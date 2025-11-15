package service;

import config.DatabaseConnection;
import dao.CredencialAccesoDAO;
import dao.UsuarioDAO;
import entities.CredencialAcceso;
import entities.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servicio para la entidad Usuario (A-Service)
 * Orquesta las operaciones transaccionales compuestas (Usuario y CredencialAcceso)
 * y aplica las reglas de negocio
 */
public class UsuarioService implements GenericService<Usuario> {

    private UsuarioDAO usuarioDAO;
    private CredencialAccesoDAO credencialDAO;

    // Patron de regex simple para validación de email
    private static final Pattern EMAIL_PATTERN = 
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
        this.credencialDAO = new CredencialAccesoDAO();
    }

    /**
     * Validacion de campos de Usuario
     */
    private void validarUsuario(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty() || usuario.getUsername().length() > 30) {
            throw new IllegalArgumentException("Username es obligatorio y debe tener máx. 30 caracteres.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email es obligatorio.");
        }
        if (!EMAIL_PATTERN.matcher(usuario.getEmail()).matches()) {
            throw new IllegalArgumentException("Formato de email inválido.");
        }
    }
    
    /**
     * Validacion de campos de Credencial
     */
    private void validarCredencial(CredencialAcceso credencial) throws Exception {
         if (credencial == null) {
            throw new IllegalArgumentException("La credencial no puede ser nula.");
        }
        if (credencial.getHashPassword() == null || credencial.getHashPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("El hashPassword es obligatorio.");
        }
        if (credencial.getSalt() == null || credencial.getSalt().trim().isEmpty()) {
             throw new IllegalArgumentException("El salt es obligatorio.");
        }
    }

    /**
     * Inserta un Usuario y su CredencialAcceso asociada
     * en una operacion transaccional compuesta
     */
    @Override
    public Usuario insertar(Usuario usuario) throws Exception {
        //Validaciones de Reglas de Negocio
        validarUsuario(usuario);
        if (usuario.getCredencialId() == null) {
            throw new IllegalArgumentException("El usuario debe tener una credencial asociada para ser creado.");
        }
        validarCredencial(usuario.getCredencialId());
        
        //Gestión de la Transaccion
        Connection conn = null;
        try {
            // Inicia transacción
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            //Ejecutar operaciones compuestas (Crear B, luego A)
            
            // Paso B: Crear CredencialAcceso (B)
            // El DAO (setGeneratedId) asignará el nuevo ID al objeto credencial
            credencialDAO.crear(usuario.getCredencialId(), conn);

            // Paso A: Crear Usuario (A)
            // El UsuarioDAO usara el ID de la credencial ya creada
            usuarioDAO.crear(usuario, conn);

            //Commit
            conn.commit();
            return usuario; // Devuelve el usuario con su ID y el ID de su credencial
            
        } catch (Exception e) {
            //Rollback
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException se) {
                    System.err.println("Error haciendo rollback: " + se.getMessage());
                }
            }
            // Captura violaciones de unicidad
            if (e.getMessage().contains("Duplicate entry") || e.getMessage().contains("UNIQUE constraint failed")) {
                if (e.getMessage().contains("username")) {
                     throw new Exception("Error: El 'username' ya existe.", e);
                }
                if (e.getMessage().contains("email")) {
                     throw new Exception("Error: El 'email' ya existe.", e);
                }
                 if (e.getMessage().contains("credencial_id")) {
                     throw new Exception("Error: La credencial ya está asignada a otro usuario (Fallo regla 1-1).", e);
                }
            }
            throw new Exception("Error en Service al insertar usuario: " + e.getMessage(), e);
        } finally {
            //Restablecer y cerrar
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException se) {
                    System.err.println("Error cerrando conexión: " + se.getMessage());
                }
            }
        }
    }

    /**
     * Actualiza un Usuario y, si es necesario, su CredencialAcceso asociada
     * en una operacion transaccional
     */
    @Override
    public Usuario actualizar(Usuario usuario) throws Exception {
        if (usuario.getId() == 0) {
             throw new IllegalArgumentException("El usuario a actualizar debe tener un ID válido.");
        }
        validarUsuario(usuario);
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // Actualiza A (Usuario)
            usuarioDAO.actualizar(usuario, conn);
            
            // Actualiza B (CredencialAcceso) si existe
            if (usuario.getCredencialId() != null && usuario.getCredencialId().getId() != 0) {
                 validarCredencial(usuario.getCredencialId());
                 credencialDAO.actualizar(usuario.getCredencialId(), conn);
            }

            conn.commit();
            return usuario;
            
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException se) {
                    System.err.println("Error haciendo rollback: " + se.getMessage());
                }
            }
            throw new Exception("Error en Service al actualizar usuario: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException se) {
                    System.err.println("Error cerrando conexión: " + se.getMessage());
                }
            }
        }
    }

    /**
     * Realiza la baja logica del Usuario (A) y su CredencialAcceso (B)
     * en una sola transaccion.
     */
    @Override
    public void eliminar(long id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            //Leer el usuario para encontrar su credencial asociada
            // Usamos la misma conexion para asegurar lectura consistente
            Usuario usuarioAEliminar = usuarioDAO.leer(id, conn);
            
            if (usuarioAEliminar == null) {
                // Si el DAO devuelve null (porque no existe o ya esta eliminado)
                throw new Exception("Usuario con ID: " + id + " no encontrado o ya eliminado.");
            }

            //Eliminar B (logicamente)
            if (usuarioAEliminar.getCredencialId() != null) {
                credencialDAO.eliminar(usuarioAEliminar.getCredencialId().getId(), conn);
            }
            
            //Eliminar A (logicamente)
            usuarioDAO.eliminar(id, conn);

            conn.commit();
            
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException se) {
                    System.err.println("Error haciendo rollback: " + se.getMessage());
                }
            }
            throw new Exception("Error en Service al eliminar usuario: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException se) {
                    System.err.println("Error cerrando conexión: " + se.getMessage());
                }
            }
        }
    }

    @Override
    public Usuario getById(long id) throws Exception {
        // Los DAO SELECT ya hacen el JOIN necesario
        try (Connection conn = DatabaseConnection.getConnection()) {
            return usuarioDAO.leer(id, conn);
        } catch (Exception e) {
            throw new Exception("Error en Service al obtener usuario por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        // Los DAO SELECT ya hacen el JOIN necesario
        try (Connection conn = DatabaseConnection.getConnection()) {
            return usuarioDAO.leerTodos(conn);
        } catch (Exception e) {
            throw new Exception("Error en Service al obtener todos los usuarios: " + e.getMessage(), e);
        }
    }
    
    /**
     * Obtiene un usuario por su username (busqueda especifica)
     *
     */
    public Usuario getByUsername(String username) throws Exception {
        // Las lecturas simples pueden gestionar su propia conexion
        try (Connection conn = DatabaseConnection.getConnection()) {
            return usuarioDAO.buscarPorUsername(username, conn);
        } catch (Exception e) {
            throw new Exception("Error en Service al buscar por username: " + e.getMessage(), e);
        }
    }
}