
package ej3;

/**

 */
public class Main {
    public static void main(String[] args) {
        Universidad utn = new Universidad("TUPAD");
        
        System.out.println("1. Crear al menos 3 profesores y 5 cursos");
        Profesor p1 = new Profesor("p101", "Cosme Fulanito", "Programacion");
        Profesor p2 = new Profesor("p102", "Pepe Honguito", "Base de Datos");
        Profesor p3 = new Profesor("p103", "Sutano Mengano", "IA");
        
        utn.agregarProfesor(p1);
        utn.agregarProfesor(p2);
        utn.agregarProfesor(p3);
        
        Curso c1 = new Curso("bd102", "Intro a SQL");
        Curso c2 = new Curso("ia203","Introduccion a la IA");
        Curso c3 = new Curso("js303","JavaScript 1");
        Curso c4 = new Curso("bd103","Bases de Datos NoSQL");
        Curso c5 = new Curso("ia204", "Ingenieria de Prompts");
        
        utn.agregarCurso(c1);
        utn.agregarCurso(c2);
        utn.agregarCurso(c3);
        utn.agregarCurso(c4);
        utn.agregarCurso(c5);
        
        System.out.println("Asignar profesores a cursos. ");        
        utn.asignarProfesorACurso("bd102", "p102");
        utn.asignarProfesorACurso("bd103", "p102");
        utn.asignarProfesorACurso("ia203", "p103");
        utn.asignarProfesorACurso("ia204", "p103");
        utn.asignarProfesorACurso("js303", "p101");
        
        System.out.println("Listar cursos con su profesor y profesores con sus cursos. ");
        utn.listarCursos();
        System.out.println("--");
        utn.listarProfesores();
        
        
        System.out.println("Cambiar el profesor de un curso y verificar que ambos lados quedan sincronizados.");
        utn.asignarProfesorACurso("ia203", "p102");
        System.out.println("Verificar sincronizacion despues del cambio");
        utn.listarProfesores();
        
        System.out.println("Remover un curso y confirmar que ya no aparece en la lista del profesor. ");
        utn.eliminarCurso("bd102");
        System.out.println("Verificar que Pepe Honguito ya no tiene ese curso");
        utn.buscarProfesorPorId("p102").listarCursos();
        utn.listarCursos();
        
        System.out.println("Remover un profesor y dejar profesor = null,  ");
        System.out.println("Eliminando al profesor p103 Sutano Mengano");
        utn.eliminarProfesor("p103");
        System.out.println("Verificar que sus cursos ahora no tienen profesor");
        utn.listarCursos();
        
        
        System.out.println("Mostrar un reporte: cantidad de cursos por profesor. ");
        for (Profesor p : utn.profesores) {
            System.out.println("- " + p.getNombre() + ": " + p.getCursos().size() + " cursos.");
        }   
    }
}
