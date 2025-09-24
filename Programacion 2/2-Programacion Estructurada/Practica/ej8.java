package tp2;

import java.util.Scanner;

/**
 Cálculo del Precio Final con impuesto y descuento.  
Crea un método calcularPrecioFinal(double impuesto, double 
descuento) que calcule el precio final de un producto en un e-commerce. La 
fórmula es: 
PrecioFinal = PrecioBase + (PrecioBase×Impuesto) − (PrecioBase×Descuento) 
PrecioFinal = PrecioBase + (PrecioBase \times Impuesto) - (PrecioBase \times 
Descuento) 
Desde main(), solicita el precio base del producto, el porcentaje de 
impuesto y el porcentaje de descuento, llama al método y muestra el precio 
final. 
Ejemplo de entrada/salida: 
Ingrese el precio base del producto: 100 
Ingrese el impuesto en porcentaje (Ejemplo: 10 para 10%): 10 
Ingrese el descuento en porcentaje (Ejemplo: 5 para 5%): 5 
El precio final del producto es: 105.0
 */
public class ej8 {
    public static void main(String[]args){
        Scanner sc= new Scanner(System.in).useDelimiter("\n");
        double precioBase, impuesto, descuento, precioFinal;
        
        System.out.println("Ingrese el precio: ");
        precioBase= sc.nextDouble();
        System.out.println("Ingrese el porcentaje de impuesto:");
        impuesto= sc.nextDouble();
        System.out.println("Ingrese el porcentaje de descuento:");
        descuento= sc.nextDouble();
        System.out.println("El precio final es: "+ calcularPrecioFinal(precioBase, descuento, impuesto));       
    }

//Funcion
public static double calcularPrecioFinal(double precio, double desc, double imp){
    double precioF= precio + (precio*imp/100) - (precio*desc/100);
    return precioF;
}
}