package service;

import config.DatabaseConnection;
import dao.CredencialAccesoDAO;
import dao.GenericDao;
import entities.Usuario;
import entities.CredencialAcceso;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación del servicio de negocio para la entidad Usuario (A).
 * Responsable de la gestión de transacciones (commit/rollback) que involucran
 * a Usuario y CredencialAcceso, aplicando las reglas de negocio.
 * * Patrón: Service Layer con Transacciones Obligatorias.
 */
public class UsuarioService implements GenericService<Usuario> {

    // DAOs inyectados. Necesitamos el DAO concreto de CredencialAcceso
    // para el método auxiliar de vínculo.
    private final GenericDao<Usuario> usuarioDao;
    private final CredencialAccesoDAO credencialAccesoDao; // Necesario para el método vincularConUsuario

    public UsuarioService(GenericDao<Usuario> usuarioDao, GenericDao<CredencialAcceso> credencialAccesoDao) {
        if (usuarioDao == null || credencialAccesoDao == null || !(credencialAccesoDao instanceof CredencialAccesoDAO)) {
            // Se valida que el DAO de CredencialAcceso sea la implementación concreta
            throw new IllegalArgumentException("Los DAOs no pueden ser null y CredencialAccesoDAO debe ser la implementación concreta para gestionar la FK.");
        }
        this.usuarioDao = usuarioDao;
        // Casteamos el GenericDao al tipo concreto para acceder a métodos específicos (vincularConUsuario)
        this.credencialAccesoDao = (CredencialAccesoDAO) credencialAccesoDao; 
    }

    /**
     * Inserta un nuevo Usuario y su CredencialAcceso asociado en una transacción.
     * * REQUISITO TFI: Transacción obligatoria (commit/rollback).
     */
    @Override
    public Usuario insertar(Usuario usuario) throws Exception {
        Connection conn = null;
        try {
            // Establecer fecha de registro y activarlo por defecto (regla de negocio)
            usuario.setFechaRegistro(LocalDateTime.now());
            usuario.setActivo(true);
            
            // 1. Iniciar Conexión y Transacción
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // **Transacción ON**

            // 2. Validaciones de Negocio
            validateUsuario(usuario);
            validateCredencial(usuario.getCredencial_id());

            // 3. Insertar CredencialAcceso (B) primero para obtener su ID
            CredencialAcceso credencial = usuario.getCredencial_id();
            credencial = credencialAccesoDao.crear(credencial, conn); 

            // 4. Insertar Usuario (A) y obtener su ID (PK autogenerada)
            usuario = usuarioDao.crear(usuario, conn); 

            // 5. Vincular B con A (usando el ID generado del Usuario)
            credencialAccesoDao.vincularConUsuario(credencial.getId(), usuario.getId(), conn);
            
            // Asignar el objeto Credencial completo al Usuario (para el objeto devuelto)
            usuario.setCredencial_id(credencial);

            // 6. Commit (Si todas las operaciones fueron exitosas)
            conn.commit(); 
            return usuario;
            
        } catch (Exception e) {
            // 7. Rollback (Si ocurre cualquier excepción)
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Error al realizar rollback: " + rollbackEx.getMessage());
                }
            }
            throw new Exception("Fallo transaccional al crear Usuario y Credencial. Transacción revertida. Causa: " + e.getMessage(), e);
        } finally {
            // 8. Cerrar recursos y restablecer auto-commit
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error al cerrar conexión: " + closeEx.getMessage());
                }
            }
        }
    }

    /**
     * Actualiza un Usuario y su CredencialAcceso asociado en una transacción.
     * * REQUISITO TFI: Transacción obligatoria (commit/rollback).
     */
    @Override
    public Usuario actualizar(Usuario usuario) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // **Transacción ON**
            
            // 1. Validaciones
            validateUsuario(usuario);
            
            // 2. Actualizar Credencial (si existe y tiene ID)
            if (usuario.getCredencial_id() != null && usuario.getCredencial_id().getId() > 0) {
                validateCredencial(usuario.getCredencial_id());
                credencialAccesoDao.actualizar(usuario.getCredencial_id(), conn);
            }
            
            // 3. Actualizar Usuario
            usuario = usuarioDao.actualizar(usuario, conn);
            
            // 4. Commit
            conn.commit();
            return usuario;
        } catch (Exception e) {
            // 5. Rollback
            if (conn != null) conn.rollback();
            throw new Exception("Fallo transaccional al actualizar Usuario. Transacción revertida. Causa: " + e.getMessage(), e);
        } finally {
            // 6. Cerrar recursos
            if (conn != null) { conn.setAutoCommit(true); conn.close(); }
        }
    }

    /**
     * Realiza una baja lógica del Usuario. Se asume que la BD (ON DELETE CASCADE) 
     * o lógica adicional gestionará la Credencial.
     */
    @Override
    public void eliminar(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de eliminación debe ser un número positivo.");
        }
        try (Connection conn = DatabaseConnection.getConnection()) {
            usuarioDao.eliminar(id, conn);
        } catch (Exception e) {
            throw new Exception("Error al realizar baja lógica de Usuario. Causa: " + e.getMessage(), e);
        }
    }

    /**
     * Busca un Usuario por su ID y carga su CredencialAcceso.
     */
    @Override
    public Usuario getById(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de búsqueda debe ser un número positivo.");
        }
        try (Connection conn = DatabaseConnection.getConnection()) {
            Usuario usuario = usuarioDao.leer(id, conn);
            
            // Lógica para cargar Credencial (se requeriría un método en DAO para buscar por FK: usuario_id)
            // Por simplicidad del TFI, la carga del objeto B en el A puede dejarse pendiente,
            // pero el Service tiene la responsabilidad de hacerlo.
            
            return usuario;
        } catch (SQLException e) {
            throw new Exception("Error al buscar Usuario por ID.", e);
        }
    }

    /**
     * Obtiene todos los Usuarios activos.
     */
    @Override
    public List<Usuario> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Nota: Este método no carga las Credenciales asociadas por defecto
            return usuarioDao.leerTodos(conn);
        } catch (SQLException e) {
            throw new Exception("Error al listar todos los Usuarios.", e);
        }
    }

    /**
     * Valida las reglas de negocio de la entidad Usuario.
     */
    private void validateUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El Usuario no puede ser null.");
        }
        // RN-004: Campos obligatorios y formato
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("RN-004: El nombre de usuario es obligatorio.");
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@") || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("RN-004: El formato del email es inválido o está vacío.");
        }
        // RN-005: Todo Usuario debe tener una CredencialAcceso
        if (usuario.getCredencial_id() == null) {
            throw new IllegalArgumentException("RN-005: Todo Usuario debe tener una CredencialAcceso asociada (relación 1:1).");
        }
        // *Falta* la validación de unicidad de username/email si la BD lo requiere.
    }
    
    /**
     * Valida las reglas de negocio de la CredencialAcceso (delegado, pero aquí 
     * se aplica como parte del Usuario).
     */
    private void validateCredencial(CredencialAcceso credencial) {
        // RN-001: El hash de la contraseña debe ser un valor seguro (mínimo 10 caracteres)
        if (credencial.getHashPassword() == null || credencial.getHashPassword().trim().length() < 10) {
            throw new IllegalArgumentException("RN-001: El hash de la contraseña de la Credencial debe ser seguro (mínimo 10 caracteres).");
        }
    }
}