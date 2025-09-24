package tp2;

import java.util.Scanner;

/**
Declara una variable global Ejemplo de entrada/salida: = 0.10. Luego, crea un 
método calcularDescuentoEspecial(double precio) que use la variable global para 
calcular el descuento especial del 10%. 
Dentro del método, declara una variable local descuentoAplicado, almacena 
el valor del descuento y muestra el precio final con descuento. 
Ejemplo de entrada/salida: 
Ingrese el precio del producto: 200 
El descuento especial aplicado es: 20.0 
El precio final con descuento es: 180.0
 */
public class ej11 {
    static double descuento=0.1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        double precio;
        
        System.out.print("Ingrese el precio del producto: ");
        precio = sc.nextDouble();
        
        calcularDescuentoEspecial(precio);
    }
    public static void calcularDescuentoEspecial(double precio){
        double descuentoAplicado;
        
        descuentoAplicado = precio * descuento;
        System.out.println("El descuento especial aplicado es: " + descuentoAplicado);
        System.out.println("El precio final con descuento es: " + (precio-descuentoAplicado));
    }            
}