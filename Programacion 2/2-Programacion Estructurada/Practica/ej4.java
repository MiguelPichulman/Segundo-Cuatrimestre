package tp2;

import java.util.Scanner;

/**
 Calculadora de Descuento según categoría.
Escribe un programa que solicite al usuario el precio de un producto y
su categoría (A, B o C).
Luego, aplique los siguientes descuentos:
Categoría A: 10% de descuento
Categoría B: 15% de descuento
Categoría C: 20% de descuento
El programa debe mostrar el precio original, el descuento aplicado y el
precio final
 */
public class ej4 {
    public static final float DESCUENTO_A = 0.90f;
    public static final float DESCUENTO_B = 0.85f;
    public static final float DESCUENTO_C = 0.80f;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double precio, precioFinal = 0;
        char categoria;
        System.out.println("Ingrese el precio: ");
        precio= sc.nextDouble();
        sc.nextLine();
        System.out.println("Ingrese una categoria (A / B / C): ");
        categoria= sc.nextLine().toLowerCase().charAt(0);
        switch (categoria){
            case 'a' :
                precioFinal= precio * DESCUENTO_A;
                System.out.println("El precio final es:" + precioFinal);
                break;
            case 'b' :
                precioFinal= precio * DESCUENTO_B;
                System.out.println("El precio final es:" + precioFinal);
                break;
            case 'c' :
                precioFinal= precio * DESCUENTO_C;
                System.out.println("El precio final es:" + precioFinal);
                break;
            default :
                System.out.println("La categoria ingresada no es correcta");
                break;
        }
        
    }
}
