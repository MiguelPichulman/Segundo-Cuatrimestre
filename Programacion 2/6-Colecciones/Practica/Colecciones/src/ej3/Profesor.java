
package ej3;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class Profesor {
    String id;
    String nombre;
    String especialidad;
    List<Curso> cursos = new ArrayList<>();

    public Profesor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cursos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

            
    public void agregarCurso(Curso c){
        if(!this.cursos.contains(c)){
            this.cursos.add(c);
            c.setProfesor(this);
        }
        
    }
    
    public void eliminarCurso (Curso c){
        if(this.cursos.contains(c)){
            this.cursos.remove(c);
            c.setProfesor(null);
    }
        
    }
    
    public void listarCursos(){
        if (cursos.isEmpty()) {
        System.out.println("  (Ninguno)");
        return;
    }
    for (Curso c : cursos) {
        System.out.println("[" + c.getCodigo() + "] " + c.getNombre());
    }
        
    }   
    
    public void mostrarInfo(){
        System.out.println("Nombre del Profesor: "+nombre);
        System.out.println("ID: "+id);
        System.out.println("Especialiad: "+especialidad);
        System.out.println("Cantidad de Cursos: "+cursos.size());
    }
}