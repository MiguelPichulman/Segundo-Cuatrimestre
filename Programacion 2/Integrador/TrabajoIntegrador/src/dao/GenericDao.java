package dao;// capa encargada de ejecutar sentencias SQL contra la BD
import java.sql.Connection;
import java.util.List;

/**
 * Interfaz generica  que define el CRUD que todos los DAOs del proyecto 
 * deben implementar
 */
public interface GenericDAO<T> {

    /**
     * Inserta un nuevo registro de la entidad en la base de datos
     */
    void crear(T entity, Connection conn) throws Exception;

    /**
     * Busca una entidad por su ID excluyendo registros con baja logica
     */
    T leer(long id, Connection conn) throws Exception;

    /**
     * Lista todas las entidades que no tienen baja logica (eliminado = false)
     */
    List<T> leerTodos(Connection conn) throws Exception;

    /**
     * Actualiza un registro existente de la entidad
     */
    void actualizar(T entity, Connection conn) throws Exception;

    /**
     * Realiza una baja logica ('eliminado' = true) de la entidad
     */
    void eliminar(long id, Connection conn) throws Exception;
}