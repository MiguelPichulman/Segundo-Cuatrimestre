package intropoo;

import java.util.Scanner;

/**
Encapsulamiento con la Clase Libro
a. Crear una clase Libro con atributos privados: titulo, autor, añoPublicacion.
Métodos requeridos: Getters para todos los atributos. Setter con validación para añoPublicacion.
Tarea: Crear un libro, intentar modificar el año con un valor inválido y luego con uno válido, mostrar la información final.
 */
class Libro{
    private String titulo, autor;
    private int anioDePublicacion;

    public Libro(String titulo, String autor, int anioDePublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioDePublicacion = anioDePublicacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAnioDePublicacion(int anioDePublicacion) {
        if (anioDePublicacion>1940 && anioDePublicacion<2025){                
            this.anioDePublicacion = anioDePublicacion;
        }else{
            System.out.println("Año de publicacion incorrecto");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioDePublicacion() {
        return anioDePublicacion;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", autor=" + autor + ", anioDePublicacion=" + anioDePublicacion + '}';
    }   
}


public class ej3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int anioMod;
        Libro librito = new Libro("Mi planta de Naranja Lima", "Jose Mauro de Vasconcelos", 1968);
        System.out.println(librito.toString());
        do{
        System.out.print("Ingrese el año de publicacion del libro: ");
        anioMod= sc.nextInt();
        librito.setAnioDePublicacion(anioMod);
        }while(anioMod<1940 || anioMod>2025);
        
        System.out.println(librito.toString());
    }
}
