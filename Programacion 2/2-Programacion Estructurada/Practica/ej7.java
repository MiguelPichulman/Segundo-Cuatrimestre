/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp2;

import java.util.Scanner;

/**
 Validación de Nota entre 0 y 10 (do-while).
Escribe un programa que solicite al usuario una nota entre 0 y 10. Si el
usuario ingresa un número fuera de este rango, debe seguir pidiéndole la nota
hasta que ingrese un valor válido.
Ejemplo de entrada/salida:
Ingrese una nota (0-10): 15
Error: Nota inválida. Ingrese una nota entre 0 y 10.
Ingrese una nota (0-10): -2
Error: Nota inválida. Ingrese una nota entre 0 y 10.
Ingrese una nota (0-10): 8
Nota guardada correctamente.
 */
public class ej7 {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in).useDelimiter("\n");
        int nota;
        boolean bandera= false;
        do{
            System.out.println("Ingrese una nota entre 0 y 10: ");
            nota= sc.nextInt();
            if((nota>=0)&&(nota<=10)){
                bandera= true;
            }
            else{
                System.out.println("Nota invalida. Ingrese una nueva nota");
            }            
        }while(!bandera);
        System.out.println("Nota guardada correctamente");        
    }    
}
