package service;
/*
Orquesta a los DAOs, aplica la logica de negocio (validaciones)
y gestiona las transacciones (commit/rollback).
*/
import entities.Base;
import java.util.List;

/*
Interfaz generica que define el contrato para la capa de servicios
*/
public interface GenericService<T extends Base> {
    
    // Los metodos lanzan Exception para que la capa principal los maneje
    
    T insertar(T entity) throws Exception;
    
    T actualizar(T entity) throws Exception;
    
    void eliminar(long id) throws Exception;
    
    T getById(long id) throws Exception;
    
    List<T> getAll() throws Exception;
}