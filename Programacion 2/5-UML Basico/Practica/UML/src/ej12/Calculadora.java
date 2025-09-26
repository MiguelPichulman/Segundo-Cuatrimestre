/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej12;

/**
 *
 * @author migue
 */
public class Calculadora {
    public void calcular(Impuesto impuesto) {
        // Simula un calculo con impuesto recibido
        System.out.println("Calculando impuesto.");
        System.out.println("Monto: $" + impuesto.getMonto());
        System.out.println("Contribuyente: " + impuesto.getContribuyente().getNombre());
        System.out.println("CUIL: " + impuesto.getContribuyente().getCuil());
    }
}