package tp2;

import java.util.Scanner;

/**
 Contador de Positivos, Negativos y Ceros (for).
Escribe un programa que pida al usuario ingresar 10 números enteros y
cuente cuántos son positivos, negativos y cuántos son ceros.

 */
public class ej6 {
    public static void main (String[] Args){
        Scanner sc= new Scanner(System.in).useDelimiter("\n");
        int num, contadorPositivos=0, contadorNegativos=0, contadorCeros=0;
        
        System.out.println("Se le pedira que ingrese 10 numeros");
        for (int i = 0; i < 10; i++) {
            System.out.println("Ingrese el numero: ");
            num=sc.nextInt();
            if (num>0){
                contadorPositivos++;
            }
                else if (num<0){
                        contadorNegativos++;
                }
                        else{
                        contadorCeros++;
                        }
        }
        System.out.println("Cantida de positivos: "+contadorPositivos);    
        System.out.println("Cantida de negativos: "+contadorNegativos);    
        System.out.println("Cantida de ceros: "+ contadorCeros);    
    }
}
