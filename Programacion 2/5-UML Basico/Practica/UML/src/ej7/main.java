/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej7;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        
        Motor motor = new Motor("V8", "SN123456789");

        // Crear un conductor
        Conductor conductor = new Conductor("Luis Gómez", "B1234567");

        // Crear un vehículo con motor y conductor
        Vehiculo vehiculo = new Vehiculo("ABC-123", "Toyota Hilux", motor, conductor);

        // Mostrar información del vehículo, motor y conductor
        System.out.println("Patente: " + vehiculo.getPatente());
        System.out.println("Modelo: " + vehiculo.getModelo());
        System.out.println("Motor tipo: " + vehiculo.getMotor().getTipo());
        System.out.println("Número de serie motor: " + vehiculo.getMotor().getNumeroSerie());
        System.out.println("Conductor: " + vehiculo.getConductor().getNombre());
        System.out.println("Licencia: " + vehiculo.getConductor().getLicencia());
    }
}
