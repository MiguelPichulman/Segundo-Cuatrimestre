package intropoo;

import java.util.Scanner;

/**
Registro de Estudiantes
a. Crear una clase Estudiante con los atributos: nombre, apellido, curso, calificación.
Métodos requeridos: mostrarInfo(), subirCalificacion(puntos), bajarCalificacion(puntos).
Tarea: Instanciar a un estudiante, mostrar su información, aumentar y disminuir calificaciones.
 */
class Estudiante{
    String nombre;
    String apellido;
    String curso;
    double calificacion;

    public Estudiante(String nombre, String apellido, String curso, double calificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso = curso;
        this.calificacion = calificacion;
    }
    
    public void mostrarInfo(){
        System.out.println("Nombre: "+this.nombre +" , Apellido: "+this.apellido+ ", Curso: "+this.curso+ ", Calificacion: "+this.calificacion);
    }
    public void subirCalificacion(double puntos){
        this.calificacion = this.calificacion + puntos;
    }
    public void bajarCalificacion(double puntos){
        this.calificacion = this.calificacion - puntos;
    }
}
public class ej1 {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante("Miguel", "Pichulman", "Programacion", 8.5);
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        
        estudiante.mostrarInfo();
        
        estudiante.subirCalificacion(1);
        estudiante.mostrarInfo();
        
        estudiante.bajarCalificacion(0.5);        
        estudiante.mostrarInfo();                
    }   
}
