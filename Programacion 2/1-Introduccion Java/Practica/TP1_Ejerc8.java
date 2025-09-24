package TP1_Ejerc8;

import java.util.Scanner;

/**
 *
 * @author migue
 */
public class TP1_Ejerc8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n1, n2;
        
        System.out.print("Ingrese un numero: ");
        n1 = input.nextInt();
        System.out.print("Ingrese otro numero: ");
        n2 = input.nextInt();
        
        System.out.println((double)n1/n2);
    }
}
