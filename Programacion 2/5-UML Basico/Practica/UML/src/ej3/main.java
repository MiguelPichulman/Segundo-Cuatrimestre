/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej3;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Autor autor = new Autor("Gabriel García Márquez", "Colombiano");

        // Crear una editorial
        Editorial editorial = new Editorial("Editorial Sudamericana", "Av. Corrientes 1234, Buenos Aires");

        // Crear un libro con autor y editorial
        Libro libro = new Libro("Cien años de soledad", "978-3-16-148410-0", autor, editorial);

        // Mostrar información del libro
        System.out.println("Título: " + libro.getTitulo());
        System.out.println("ISBN: " + libro.getIsbn());
        System.out.println("Autor: " + libro.getAutor().getNombre());
        System.out.println("Nacionalidad: " + libro.getAutor().getNacionalidad());
        System.out.println("Editorial: " + libro.getEditorial().getNombre());
        System.out.println("Dirección Editorial: " + libro.getEditorial().getDireccion());
    }
}
