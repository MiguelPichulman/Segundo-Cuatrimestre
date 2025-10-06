package colecciones;

import java.util.ArrayList;

/**

● agregarProducto(Producto p)  
● listarProductos()  
● buscarProductoPorId(String id)  
● eliminarProducto(String id)  
● actualizarStock(String id, int nuevaCantidad)  
● filtrarPorCategoria(CategoriaProducto categoria)  
● obtenerTotalStock()  
● obtenerProductoConMayorStock()  
● filtrarProductosPorPrecio(double min, double max)  
● mostrarCategoriasDisponibles() 
 */
public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();
    
    public void agregarProductos(Producto p){
        this.productos.add(p);
    }
    public void listaProductos(){
        for (Producto p : productos) {
        p.mostrarInfo();
        }    
    }
    public Producto buscarProductoPorId(String id){
        for (Producto p : productos) {
            if(p.getId().equalsIgnoreCase(id)){
                return p;
            }
        }
        return null;
    }
    
    public void eliminarProducto(String id){
            Producto p= buscarProductoPorId(id);
            if(p!= null){
                productos.remove(p);
                System.out.println("eliminado");
            }else{
                System.out.println("No se puede eliminar porque no existe");
            }
    }
    public void actualizarStock(String id, int nuevaCantidad){
        Producto p = buscarProductoPorId(id);
        if(p!=null){
          p.setCantidad(nuevaCantidad);
            System.out.println(p.getCantidad());
        }else{
            System.out.println("no se puede actualizar porque no existe");
        }
    }
    public void filtrarPorCategoria(CategoriaProducto categoria){
        for (Producto p : productos) {
            if(categoria.equals(p.categoria)){
                p.mostrarInfo();
            }
        }       
    }
    public void obtenerTotalStock(){
        int acum=0;
        for (Producto p : productos) {
            acum = acum + p.cantidad;
        }
        System.out.println("El stock Total es: "+ acum);
    }
    public void obtenerProductoConMayorStock(){
        int max= -1;
        Producto aux = new Producto();
        for(Producto p : productos){
            if(p.getCantidad()>max){
                max=p.getCantidad();
                aux=p;
            }
        }
        System.out.println("el producto con mayor stock es: " +aux.getNombre());        
    }
    
    public void filtrarProductosPorPrecio(double min, double max){
        for (Producto p : productos) {
            if((p.getPrecio()>min)&&(p.getPrecio()<max)){
                System.out.println(p);
            }
        }       
    }
    
    public void mostrarCategoriasDisponibles() {
 
    }
}
