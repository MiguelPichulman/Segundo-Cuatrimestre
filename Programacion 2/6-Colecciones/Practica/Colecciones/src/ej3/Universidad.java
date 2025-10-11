
package ej3;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class Universidad {
    String nombre;
    List<Profesor> profesores = new ArrayList<>();
    List<Curso> cursos = new ArrayList<>();

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }
    
        
    public void agregarProfesor(Profesor p){
        this.profesores.add(p);
        
    }
    
    public void agregarCurso(Curso c) {
        this.cursos.add(c);
    
    }
    
    public void asignarProfesorACurso(String codigoCurso, String idProfesor){
        Curso c = buscarCursoPorCodigo(codigoCurso);
        Profesor p = buscarProfesorPorId(idProfesor);
        
        if(c != null && p != null){
            c.setProfesor(p);
            System.out.println("Se asigno el profesor");
        }else{
            System.out.println("No se encontro el curso o el profesor");
        }        
    }

    public void listarProfesores(){
        for (Profesor p : profesores) {
            p.mostrarInfo();
        }
    }
    
    public void  listarCursos(){
        for (Curso c : cursos) {
            c.mostrarInfo();
        }
        
    }
    
    public Profesor buscarProfesorPorId(String id) {
        for (Profesor p : profesores) {
            if(p.getId().equals(id)){
               return p; 
            }
        }
        return null;
    }
    
    public Curso buscarCursoPorCodigo(String codigo){
        for (Curso c : cursos) {
            if(c.getCodigo().equals(codigo)){
                return c;
            }
        }
        return null;
    }
    
    public void eliminarCurso(String codigo){
        Curso c = buscarCursoPorCodigo(codigo);
        if( c!= null){
            if(c.getProfesor() !=null){
                c.getProfesor().eliminarCurso(c);
            }
            this.cursos.remove(c);
            System.out.println("Curso eliminado");
        }
    
    }
 
    public void eliminarProfesor(String id){
        Profesor p = buscarProfesorPorId(id);
        if(p !=null){
           List<Curso> cAux = new ArrayList<>(p.getCursos());
            for (Curso c : cAux) {
                p.eliminarCurso(c);
            }
            this.profesores.remove(p);
            System.out.println("Profesor eliminado");
            
        }
    }

}
