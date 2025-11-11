
package dao;

import entities.Base;
import java.sql.Connection;
import java.util.List;

/**
 * Interfaz genérica para el patrón DAO.
 * Define las operaciones CRUD básicas para cualquier entidad que extienda de Base.
 * @param <T> El tipo de Entidad de Dominio (Ej: Usuario, CredencialAcceso)
 */
public interface GenericDao<T extends Base> {

    // 1. Crear (T)
    /**
     * Inserta un nuevo registro de la entidad T en la base de datos.
     * @param entity La entidad a insertar.
     * @param connection La conexión externa para participar en una transacción.
     * @return La entidad T con su ID generado por la base de datos.
     * @throws Exception Si ocurre un error de SQL o lógica de negocio.
     */
    T crear(T entity, Connection connection) throws Exception;

    // 2. Leer por ID (long id)
    /**
     * Busca una entidad por su ID, excluyendo registros con baja lógica.
     * @param id El identificador de la entidad.
     * @param connection La conexión externa.
     * @return La entidad encontrada o null si no existe o está eliminada.
     * @throws Exception Si ocurre un error de SQL.
     */
    T leer(long id, Connection connection) throws Exception;

    // 3. Leer Todos (leerTodos())
    /**
     * Lista todas las entidades T que no tienen baja lógica (eliminado = false).
     * @param connection La conexión externa.
     * @return Una lista de todas las entidades T.
     * @throws Exception Si ocurre un error de SQL.
     */
    List<T> leerTodos(Connection connection) throws Exception;

    // 4. Actualizar (T)
    /**
     * Actualiza un registro existente de la entidad T.
     * @param entity La entidad con los datos actualizados.
     * @param connection La conexión externa.
     * @return La entidad T actualizada.
     * @throws Exception Si ocurre un error de SQL o la entidad no existe.
     */
    T actualizar(T entity, Connection connection) throws Exception;

    // 5. Eliminar (long id) - Baja Lógica
    /**
     * Realiza una baja lógica (setting 'eliminado' to true) de la entidad T.
     * @param id El ID de la entidad a eliminar.
     * @param connection La conexión externa.
     * @throws Exception Si ocurre un error de SQL o la entidad no existe.
     */
    void eliminar(long id, Connection connection) throws Exception;
}