package tp2;

import java.util.Scanner;

/**
 Crea un método actualizarStock(int stockActual, int cantidadVendida, 
int cantidadRecibida), que calcule el nuevo stock después de una venta y 
recepción 
de productos: 
NuevoStock = StockActual − CantidadVendida + CantidadRecibida 
NuevoStock = CantidadVendida + CantidadRecibida 
Desde main(), solicita al usuario el stock actual, la cantidad vendida y la 
cantidad recibida, y muestra el stock actualizado. 
Ejemplo de entrada/salida: 
Ingrese el stock actual del producto: 50 
Ingrese la cantidad vendida: 20 
Ingrese la cantidad recibida: 30 
El nuevo stock del producto es: 60 
 */
public class ej10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int stockActual, cantidadVendida, cantidadRecibida;
        
        System.out.print("Ingrese el stock actual del producto: ");
        stockActual= sc.nextInt();
        System.out.print("Ingrese la cantidad vendida: ");
        cantidadVendida= sc.nextInt();
        System.out.print("Ingrese la cantidad recibida: ");
        cantidadRecibida= sc.nextInt();
        
        System.out.println("El nuevo stock del producto es: " + actualizarStock(stockActual, cantidadVendida, cantidadRecibida));
    }
    public static int actualizarStock(int stockActual, int cantidadVendida, int cantidadRecibida){        
        return stockActual - cantidadVendida + cantidadRecibida;
    }
}
