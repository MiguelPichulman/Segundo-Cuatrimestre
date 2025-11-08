
package ej2;

import java.util.List;

/**

 */
public class Main {
    public static void main(String[] args) {
        List<Libro> libro = null;
    
        System.out.println("//1. Creamos una biblioteca. ");
    Biblioteca biblioteca = new Biblioteca("San Martin");
        
        System.out.println("");
        System.out.println("//2. Crear al menos tres autores");
    Autor autor1 = new Autor("ABC123","Adolfo Bioy Casares","argentino");
    Autor autor2 = new Autor("DEF456","Mario Benedetti","uruguayo");
    Autor autor3 = new Autor("GHI/()","Gabriel Garcia Marquez","colombiano");
    
        System.out.println("");
        System.out.println("//3. Agregar 5 libros asociados a alguno de los Autores a la biblioteca.");
    biblioteca.agregarLibro("L1B1", "La invencion de Morel",1940, autor1);
    
    biblioteca.agregarLibro("L2B1", "De un mundo a otro",1984, autor1);//se modifico el año de publicacion
    
    biblioteca.agregarLibro("L4B1", "Geografias",1984, autor2);
    
    biblioteca.agregarLibro("L7B1", "La hojarasca",1955, autor3);
    
    biblioteca.agregarLibro("L8B1", "En agosto nos vemos",2024, autor3);
    
        System.out.println("");
        System.out.println("//4. Listar todos los libros con su información y la del autor");
    biblioteca.listarLibros();
    
        System.out.println("");
        System.out.println("//5. Buscar un libro por su ISBN y mostrar su información.");
    biblioteca.buscarLibroPorIsbn("L4B1");
    
    
        System.out.println("");
        System.out.println("//6. Filtrar y mostrar los libros publicados en un año específico. ");
    biblioteca.filtrarLibrosPorAnio(2024);
    biblioteca.filtrarLibrosPorAnio(1984);
    biblioteca.filtrarLibrosPorAnio(2025);
    
        System.out.println("");
        System.out.println("//7. Eliminar un libro por su ISBN y listar los libros restantes. ");
    biblioteca.eliminarLibro("L1B1");
    biblioteca.listarLibros();
    
        System.out.println("");
        System.out.println("//8. Mostrar la cantidad total de libros en la biblioteca.");
    biblioteca.obtenerCantidadLibros();
    
        System.out.println("");
        System.out.println("//9. Listar todos los autores de los libros disponibles en la biblioteca.");
    biblioteca.mostrarAutoresDisponibles();
        
    }
}
