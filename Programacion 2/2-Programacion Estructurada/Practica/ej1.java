package tp2;

import java.util.Scanner;

/**
Escribe un programa en Java que solicite al usuario un año y determine si es 
bisiesto. Un año es bisiesto si es divisible por 4, pero no por 100, salvo que sea 
divisible por 400. 
Ejemplo de entrada/salida: 
Ingrese un año: 2024 
El año 2024 es bisiesto. 
Ingrese un año: 1900 
El año 1900 no es bisiesto.
 */
public class ej1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.print("Ingrese un año: ");
        int año = scanner.nextInt();

        if ((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) {
            System.out.println("El año " + año + " es bisiesto.");
        } else {
            System.out.println("El año " + año + " no es bisiesto.");
        }
    }
}
