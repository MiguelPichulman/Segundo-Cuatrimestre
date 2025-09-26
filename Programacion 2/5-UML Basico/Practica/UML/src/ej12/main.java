/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej12;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Contribuyente contribuyente = new Contribuyente("Ana Perez", "20-12345678-9");

        // Crear un impuesto con contribuyente
        Impuesto impuesto = new Impuesto(1500.50, contribuyente);

        // Crear una calculadora
        Calculadora calculadora = new Calculadora();

        // Calcular impuesto
        calculadora.calcular(impuesto);
    }
}
