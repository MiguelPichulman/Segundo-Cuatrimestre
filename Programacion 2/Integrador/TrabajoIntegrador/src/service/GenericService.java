package service;

import entities.Base;
import java.util.List;

public interface GenericService<T extends Base> {
    
    // Los metodos lanzan Exception para que la capa principal los maneje
    
    T insertar(T entity) throws Exception;
    
    T actualizar(T entity) throws Exception;
    
    void eliminar(long id) throws Exception;
    
    T getById(long id) throws Exception;
    
    List<T> getAll() throws Exception;
}