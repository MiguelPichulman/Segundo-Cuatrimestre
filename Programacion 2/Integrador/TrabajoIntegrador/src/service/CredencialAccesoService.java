package service;

import config.DatabaseConnection;
import dao.CredencialAccesoDAO;
import entities.CredencialAcceso;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para la entidad CredencialAcceso (BService).
 * Gestiona las transacciones y validaciones de negocio.
 */
public class CredencialAccesoService implements GenericService<CredencialAcceso> {

    private CredencialAccesoDAO credencialDAO;

    public CredencialAccesoService() {
        this.credencialDAO = new CredencialAccesoDAO();
    }

    /**
     * Valida los campos obligatorios de una CredencialAcceso
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
     * Inserta una credencial.
     * NOTA: Este metodo NO debe usarse para el alta de Usuario (eso lo hace
     * UsuarioService). Existe por coherencia de la interfaz GenericService.
     */
    @Override
    public CredencialAcceso insertar(CredencialAcceso credencial) throws Exception {
        validarCredencial(credencial);
        
        Connection conn = null;
        try {
            // Inicia transaccion
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            credencialDAO.crear(credencial, conn);

            // Commit
            conn.commit();
            return credencial; // Devuelve la entidad con el ID asignado
            
        } catch (Exception e) {
            // Rollback
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException se) {
                    System.err.println("Error haciendo rollback: " + se.getMessage());
                }
            }
            throw new Exception("Error en Service al insertar credencial: " + e.getMessage(), e);
        } finally {
            // Restablece autocommit y cierra
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
     * Actualiza una credencial (ej. cambio de contraseña).
     * Esta sí es una operacion transaccional valida para este servicio.
     */
    @Override
    public CredencialAcceso actualizar(CredencialAcceso credencial) throws Exception {
        if (credencial.getId() == 0) {
             throw new IllegalArgumentException("La credencial a actualizar debe tener un ID válido.");
        }
        validarCredencial(credencial);
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            credencialDAO.actualizar(credencial, conn);

            conn.commit();
            return credencial;
            
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException se) {
                    System.err.println("Error haciendo rollback: " + se.getMessage());
                }
            }
            throw new Exception("Error en Service al actualizar credencial: " + e.getMessage(), e);
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
     * Elimina una credencial logicamente.
     * NOTA: Esto no deberia llamarse directamente. Se debe llamar
     * a UsuarioService.eliminar() para mantener la integridad transaccional.
     */
    @Override
    public void eliminar(long id) throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            credencialDAO.eliminar(id, conn); // Baja logica

            conn.commit();
            
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException se) {
                    System.err.println("Error haciendo rollback: " + se.getMessage());
                }
            }
            throw new Exception("Error en Service al eliminar credencial: " + e.getMessage(), e);
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
    public CredencialAcceso getById(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return credencialDAO.leer(id, conn);
        } catch (Exception e) {
            throw new Exception("Error en Service al obtener credencial por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CredencialAcceso> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return credencialDAO.leerTodos(conn);
        } catch (Exception e) {
            throw new Exception("Error en Service al obtener todas las credenciales: " + e.getMessage(), e);
        }
    }
}