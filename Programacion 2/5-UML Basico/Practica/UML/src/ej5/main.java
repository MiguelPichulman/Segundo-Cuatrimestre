/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej5;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
       Propietario propietario = new Propietario("Carlos López", "30456789");

        // Crear una computadora con placa madre y propietario
        Computadora computadora = new Computadora(
            "Dell",
            "SN123456789",
            "ASUS Prime",
            "Intel Z490",
            propietario
        );

        // Mostrar información de la computadora, placa madre y propietario
        System.out.println("Marca Computadora: " + computadora.getMarca());
        System.out.println("Número de Serie: " + computadora.getNumeroSerie());
        System.out.println("Modelo Placa Madre: " + computadora.getPlacaMadre().getModelo());
        System.out.println("Chipset Placa Madre: " + computadora.getPlacaMadre().getChipset());
        System.out.println("Propietario: " + computadora.getPropietario().getNombre());
        System.out.println("DNI Propietario: " + computadora.getPropietario().getDni()); 
    }
}
