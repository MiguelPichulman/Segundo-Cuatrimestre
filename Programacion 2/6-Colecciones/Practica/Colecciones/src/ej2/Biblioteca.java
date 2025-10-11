
package ej2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**

 */
public class Biblioteca {
    String nombre;
    List<Libro> libros;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
    }  
    
    
    public void agregarLibro(String isbn, String titulo,int anioPublicacion, Autor autor){
        Libro l = new Libro (isbn, titulo, anioPublicacion, autor);
        libros.add(l);
    }
    public void listarLibros(){
        for(Libro l : libros){
            System.out.println(l.getTitulo());
        }
    }
    
    public  void buscarLibroPorIsbn(String isbn) {
        for (Libro l : libros){
            if(l.getIsbn().equalsIgnoreCase(isbn)){
                System.out.println("El libro se encuentra en la biblioteca");
                System.out.println("Nombre: "+l.getTitulo());
                System.out.println("ISBN: "+l.getIsbn());
            }
        }
    }
    
    public void eliminarLibro(String isbn) {
        libros.removeIf(libro -> libro.getIsbn().equals(isbn));

//        for(Libro l : libros){
//            if(l.getIsbn().equalsIgnoreCase(isbn)){
//                libros.remove(l);
//            }
//        }
    }
    
    public void obtenerCantidadLibros() {
        System.out.println("Cantidad de libros: "+libros.size());
    }
    
    public void filtrarLibrosPorAnio(int anio) {
        for (Libro l : libros) {
            if(l.getAnioPublicacion() == anio){
                System.out.println("Libro: "+l.getTitulo());
            }
        }
                
    }
    public void mostrarAutoresDisponibles() {
        Set<String> autores = new HashSet<>();
        for (Libro l : libros) {
            autores.add(l.getAutor().getNombre());
        }
        for (String a : autores) {
            System.out.println("Autor: "+a);
        }
    }
}
