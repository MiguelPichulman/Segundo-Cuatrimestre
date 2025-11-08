
package ej3;

/**

 */
public class Curso {
    String codigo;
    String nombre;
    Profesor profesor;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }
    
    public void setProfesor(Profesor profesor) {
        if (this.profesor==profesor){
            return;
        }
        Profesor profesorAnt = this.profesor;
        this.profesor= profesor;
        
        if(profesorAnt != null){
            profesorAnt.eliminarCurso(this);
        }
        
        if(profesor!=null){
            profesor.agregarCurso(this);
            
        }
    }
    
    public void mostrarInfo(){
        System.out.println("Codigo del Curso: "+codigo);
        System.out.println("Nombre del Curso: "+nombre);
        if (profesor != null) {
        System.out.println("Profesor a cargo: " + profesor.getNombre());
    } else {
        System.out.println("Profesor a cargo: (Sin asignar)");
    }
        
    }
}