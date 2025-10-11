
package ej2;

/**
 
 */
public class Libro {
    String isbn;
    String titulo;
    int anioPublicacion;
    Autor autor;

    public Libro() {
    }
    
    
    public Libro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }
    
    public void mostrarInfo(){
        System.out.println("Identificador del Libro: "+isbn);
        System.out.println("Titulo del Libro: "+titulo);
        System.out.println("Año de Publicacion: "+anioPublicacion);
        System.out.println("Autor del Libro: "+autor);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public Autor getAutor() {
        return autor;
    }
    
}
