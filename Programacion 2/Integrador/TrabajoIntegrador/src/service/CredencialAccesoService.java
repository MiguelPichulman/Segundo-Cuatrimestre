package service;

import config.DatabaseConnection;
import dao.GenericDao;
import entities.CredencialAcceso;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementación del servicio de negocio para la entidad CredencialAcceso (B).
 * Es una capa delgada, ya que las operaciones transaccionales complejas (1:1)
 * son responsabilidad del UsuarioService.
 *
 * Responsabilidades:
 * - Aplicar validaciones de la entidad CredencialAcceso.
 * - Rechazar operaciones que deban ser transaccionales y centralizadas (insertar, eliminar).
 * - Delegar operaciones de lectura y actualización al DAO.
 */
public class CredencialAccesoService implements GenericService<CredencialAcceso> {
    
    // Inyección de dependencia del DAO
    private final GenericDao<CredencialAcceso> credencialAccesoDao;

    public CredencialAccesoService(GenericDao<CredencialAcceso> credencialAccesoDao) {
        if (credencialAccesoDao == null) {
            throw new IllegalArgumentException("CredencialAccesoDAO no puede ser null.");
        }
        this.credencialAccesoDao = credencialAccesoDao;
    }

    /**
     * Inserta una nueva credencial en la base de datos.
     * * REGLA DE NEGOCIO: La creación debe ser orquestada por UsuarioService
     * * para asegurar la relación 1:1 transaccional.
     * @throws UnsupportedOperationException Siempre, ya que la inserción está centralizada.
     */
    @Override
    public CredencialAcceso insertar(CredencialAcceso entity) throws Exception {
        throw new UnsupportedOperationException("La creación de CredencialAcceso debe realizarse a través de UsuarioService.insertar() para mantener la integridad de la relación 1:1.");
    }

    /**
     * Actualiza un registro existente de CredencialAcceso.
     *
     * @param entity La credencial con los datos a actualizar.
     * @return La credencial actualizada.
     * @throws Exception Si hay error de conexión/BD o fallan las validaciones.
     */
    @Override
    public CredencialAcceso actualizar(CredencialAcceso entity) throws Exception {
        validateCredencial(entity);
        try (Connection conn = DatabaseConnection.getConnection()) {
            // El actualizar se realiza en una conexión no transaccional si es llamado directamente
            return credencialAccesoDao.actualizar(entity, conn);
        } catch (SQLException e) {
            throw new Exception("Error al actualizar CredencialAcceso en la base de datos.", e);
        }
    }

    /**
     * Realiza una baja lógica de la CredencialAcceso por su ID.
     * * REGLA DE NEGOCIO: La eliminación debe ser orquestada por UsuarioService
     * * o por la restricción ON DELETE CASCADE de la BD.
     * @throws UnsupportedOperationException Siempre, ya que la eliminación está centralizada.
     */
    @Override
    public void eliminar(long id) throws Exception {
        throw new UnsupportedOperationException("La eliminación de CredencialAcceso debe realizarse a través de UsuarioService.eliminar() para mantener la integridad de la relación 1:1.");
    }

    /**
     * Busca una CredencialAcceso por su ID.
     */
    @Override
    public CredencialAcceso getById(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la Credencial debe ser un número positivo.");
        }
        try (Connection conn = DatabaseConnection.getConnection()) {
            return credencialAccesoDao.leer(id, conn);
        } catch (SQLException e) {
            throw new Exception("Error al buscar CredencialAcceso por ID.", e);
        }
    }

    /**
     * Obtiene una lista de todas las Credenciales de Acceso activas.
     */
    @Override
    public List<CredencialAcceso> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return credencialAccesoDao.leerTodos(conn);
        } catch (SQLException e) {
            throw new Exception("Error al listar todas las Credenciales de Acceso.", e);
        }
    }
    
    /**
     * Valida que la credencial tenga datos correctos.
     * @param credencial Credencial a validar
     * @throws IllegalArgumentException Si alguna validación falla
     */
    private void validateCredencial(CredencialAcceso credencial) {
        if (credencial == null) {
            throw new IllegalArgumentException("La CredencialAcceso no puede ser null.");
        }
        // RN-001: El hash de la contraseña debe ser un valor seguro (mínimo 10 caracteres)
        if (credencial.getHashPassword() == null || credencial.getHashPassword().trim().length() < 10) {
            throw new IllegalArgumentException("RN-001: El hash de la contraseña debe ser un valor seguro (mínimo 10 caracteres).");
        }
        // No se requiere validar salt si puede ser null en BD, solo si se usa.
    }
}