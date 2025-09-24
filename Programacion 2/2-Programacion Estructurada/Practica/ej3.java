package tp2;

import java.util.Scanner;

/**
 Escribe un programa en Java que solicite al usuario su edad y clasifique su
etapa de vida según la siguiente tabla:
Menor de 12 años: "Niño"
Entre 12 y 17 años: "Adolescente"
Entre 18 y 59 años: "Adulto"
60 años o más: "Adulto mayor"
 */
public class ej3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int edad;
        System.out.println("Ingrese su edad: ");
        edad = sc.nextInt();
        if (edad < 12){
            System.out.println("Ud es un nino");
        }
            else if ((edad>=12)&&(edad<=17)){
                System.out.println("Ud es un adolescente");
            }
            else if ((edad>=18)&&(edad<=59)){
                System.out.println("Ud es un adulto");
            }
            else if(edad>=60){
                System.out.println("Ud es un adulto mayor");
            }              
    }
        
}
