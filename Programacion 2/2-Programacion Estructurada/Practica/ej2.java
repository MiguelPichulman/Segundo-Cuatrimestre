package tp2;

import java.util.Scanner;

/**
Determinar el Mayor de Tres Números. 
Escribe un programa en Java que pida al usuario tres números enteros y 
determine cuál es el mayor.  
Ejemplo de entrada/salida: 
Ingrese el primer número: 8 
Ingrese el segundo número: 12 
Ingrese el tercer número: 5 
El mayor es: 12
 */
public class ej2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int mayor;

        System.out.print("Ingrese el primer número: ");
        int num1 = scanner.nextInt();
        System.out.print("Ingrese el segundo número: ");
        int num2 = scanner.nextInt();
        System.out.print("Ingrese el tercer número: ");
        int num3 = scanner.nextInt();        

        if (num1 >= num2 && num1 >= num3) {
            mayor = num1;
        } else if (num2 >= num1 && num2 >= num3) {
            mayor = num2;
        } else {
            mayor = num3;
        }

        System.out.println("El mayor es: " + mayor);
    }
}
