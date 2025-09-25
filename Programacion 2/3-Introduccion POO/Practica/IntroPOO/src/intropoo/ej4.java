package intropoo;

import java.util.Scanner;

/**
Gestión de Gallinas en Granja Digital
a. Crear una clase Gallina con los atributos: idGallina, edad, huevosPuestos.
Métodos requeridos: ponerHuevo(), envejecer(), mostrarEstado().
Tarea: Crear dos gallinas, simular sus acciones (envejecer y poner huevos), y mostrar su estado.
 */
class Gallina{
    public int idGallina, edad, huevosPuestos;

    public Gallina(int idGallina) {
        this.idGallina = idGallina;
        this.edad = 0;
        this.huevosPuestos = 0;
    }
    //poner huevos
    public void ponerHuevos(int cantHuevos){
        //this.huevosPuestos = this.huevosPuestos + cantHuevos;
        this.setHuevosPuestos(this.huevosPuestos+cantHuevos);
    }
    //envejecer
    public void envejecer(int anios){
        this.setEdad(this.edad+anios);
    }
    
    //estado
    public void estado(){
        System.out.println("la gallina: " +this.idGallina+ " tiene " +this.edad+ " años y ha puesto " +this.huevosPuestos+ " huevos en su vida");
    }
    
    //getter y setters

    public int getIdGallina() {
        return idGallina;
    }

    public void setIdGallina(int idGallina) {
        this.idGallina = idGallina;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getHuevosPuestos() {
        return huevosPuestos;
    }

    public void setHuevosPuestos(int huevosPuestos) {
        this.huevosPuestos = huevosPuestos;
    }
    
}
public class ej4 {
    public static void main(String[] args) {
        Gallina g1 = new Gallina(1);
        Gallina g2 = new Gallina(2);
        
        g1.estado();
        g2.estado();
        
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int huevos, anios;
        
        System.out.print("ingrese la cantidad de años que han pasado para la gallina 1: ");
        g1.envejecer(sc.nextInt());
        System.out.print("ingrese la cantidad de huevos que ha colocado: ");
        g1.ponerHuevos(sc.nextInt());
        
        System.out.print("ingrese la cantidad de años que han pasado para la gallina 2: ");
        g2.envejecer(sc.nextInt());
        System.out.print("ingrese la cantidad de huevos que ha colocado: ");
        g2.ponerHuevos(sc.nextInt());
        System.out.println("paso el tiempo y......");
        g1.estado();
        g2.estado();        
    }    
}
