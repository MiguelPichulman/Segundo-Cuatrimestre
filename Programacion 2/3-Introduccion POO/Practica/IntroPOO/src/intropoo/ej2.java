package intropoo;

import java.util.Scanner;

/**
Registro de Mascotas
a. Crear una clase Mascota con los atributos: nombre, especie, edad.
Métodos requeridos: mostrarInfo(), cumplirAnios().
Tarea: Crear una mascota, mostrar su información, simular el paso del tiempo y verificar los cambios.
 */
class Mascota{
        String nombre, especie;
        int edad;

        public Mascota(String nombre, String especie, int edad) {
            this.nombre = nombre;
            this.especie = especie;
            this.edad = edad;
        }

        public void mostrarInfo() {
            System.out.println("Nombre de la mascota: "+ this.nombre + ", especie: "+this.especie+ ", edad: "+this.edad+ " años");
        }
        
        public void cumplirAnios(int anio){
            this.edad= this.edad+anio;
        }        
    }
public class ej2 {
    
    public static void main(String[] args) {
        Mascota mascotita = new Mascota("Lara", "Golden", 2);
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int cumpleanios;
        
        mascotita.mostrarInfo();
        
        System.out.println("Ingrese cuantos añitos mas cumplio su mascota: ");
        cumpleanios = sc.nextInt();
        mascotita.cumplirAnios(cumpleanios);
        mascotita.mostrarInfo();       
    }
    
}
