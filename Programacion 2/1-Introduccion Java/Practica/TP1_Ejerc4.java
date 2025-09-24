package TP1_Ejerc4;

import java.util.Scanner;

/**
 *
 * @author migue
 */
public class TP1_Ejerc4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String nombre;
        int edad;
        
        System.out.print("Ingrese su nombre: ");
        nombre = input.nextLine();
        System.out.print("Ingrese su edad: ");
        edad = input.nextInt();
        
        System.out.println("Nombre: "+nombre);
        System.out.println("Edad: "+edad+" años");
    }
}