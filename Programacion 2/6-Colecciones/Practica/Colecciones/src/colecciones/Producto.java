
package colecciones;

/**
Clases a implementar Clase Producto  
● id (String) → Identificador único del producto.  
● nombre (String) → Nombre del producto.  
● precio (double) → Precio del producto.  
● cantidad (int) → Cantidad en stock.  
● categoria (CategoriaProducto) → Categoría del producto.  
Métodos:  
● mostrarInfo() → Muestra en consola la información del producto. 
 */
public class Producto {
    String id;
    String nombre;
    double precio;
    int cantidad;
    CategoriaProducto categoria;
    
    public Producto(){
        
    }

    public Producto(String id, String nombre, double precio, int cantidad, CategoriaProducto categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }
    public Producto(String id, int cantidad){
        this.id=id;
        this.cantidad=cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }
 
   
    public void mostrarInfo(){
        System.out.println("ID Producto: "+ id);
        System.out.println("Nombre del Producto: "+ nombre);
        System.out.println("Precio del Producto: " + precio);
        System.out.println("Cantidad en Stock: "+ cantidad);
        System.out.println("Categoria: "+categoria);
        System.out.println("");
    }
}
