package tp2;

import java.util.Scanner;

/**
 Composición de funciones para calcular costo de envío y total de compra. 
a. calcularCostoEnvio(double peso, String zona): Calcula el costo de 
envío basado en la zona de envío (Nacional o Internacional) y el peso del 
paquete. 
Nacional: $5 por kg  
Internacional: $10 por kg 
b. calcularTotalCompra(double precioProducto, double 
costoEnvio): Usa calcularCostoEnvio para sumar el costo del producto con 
el costo de envío. 
Desde main(), solicita el peso del paquete, la zona de envío y el precio 
del producto. Luego, muestra el total a pagar. 
Ejemplo de entrada/salida: 
Ingrese el precio del producto: 50 
Ingrese el peso del paquete en kg: 2 
Ingrese la zona de envío (Nacional/Internacional): Nacional 
El costo de envío es: 10.0 
El total a pagar es: 60.0
 */
public class ej9 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in).useDelimiter("\n");
        double peso, precio;
        String zona;
        
        System.out.print("Ingrese el precio del producto: ");
        precio = sc.nextDouble();

        System.out.print("Ingrese el peso del paquete en Kg: ");
        peso= sc.nextDouble();
        sc.nextLine();

        System.out.print("Ingrese la zona de envio (Nacional/Internacional):  ");
        zona= sc.nextLine();
        
        System.out.println("El total a pagar es: " + calcularTotalCompra(precio, peso, zona));
        
    }
    public static double calcularCostoEnvio(double peso, String zona) {
        double envio;
        if ("Nacional".equals(zona)) {
            envio=peso*5;
            System.out.println("el costo de envio es: " + envio);
            return envio;
        }
        else if ("Internacional".equals(zona)) {
            envio= peso*10;
            System.out.println("el costo de envio es: " + envio);
            return  envio;
        }else{
            System.out.println("Zona de envío no válida.");
            return 0;
        }
    }
    public static double calcularTotalCompra(double precioProducto, double peso, String zona){
        return precioProducto + calcularCostoEnvio(peso, zona);
    }
}