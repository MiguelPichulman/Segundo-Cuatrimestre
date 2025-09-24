package tp2;

import java.util.Scanner;

/**
 Suma de Números Pares (while).
Escribe un programa que solicite números al usuario y sume solo los
números pares. El ciclo debe continuar hasta que el usuario ingrese el número
0, momento en el que se debe mostrar la suma total de los pares ingresados.
 */
public class ej5 {
    public static void main (String[] args){
        Scanner sc= new Scanner(System.in).useDelimiter("\n");
        int num, acumulador=0;
        System.out.println("Ingrese un numero para sumar. Si ingresa 0 finaliza el programa: ");
        num= sc.nextInt();
        while (num!=0){
            if(num%2==0){
                acumulador= acumulador + num;
            }
                System.out.println("Ingrese otro numero. Para terminar ingrese 0: ");
                num=sc.nextInt();
        }
        System.out.println("La suma total fue: " + acumulador);
    }
}