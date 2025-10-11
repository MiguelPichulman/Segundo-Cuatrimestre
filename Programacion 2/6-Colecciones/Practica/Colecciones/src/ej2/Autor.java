
package ej2;

/**

 */
public class Autor {
    String id;
    String nombre;
    String nacionalidad;

    public Autor(String id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }   
    
    public void mostrarInfo(){
        System.out.println("ID del autor: "+id);
        System.out.println("Nombre del autor: "+nombre);
        System.out.println("Nacionalidad del autor: "+nacionalidad);
    }
    
}
