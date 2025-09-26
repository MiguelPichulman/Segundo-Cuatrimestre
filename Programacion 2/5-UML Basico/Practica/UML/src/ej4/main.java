/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej4;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Central", "30-12345678-9");

        // Crear un cliente
        Cliente cliente = new Cliente("Laura Martínez", "26789456");

        // Crear una tarjeta de crédito con banco y cliente
        TarjetaDeCredito tarjeta = new TarjetaDeCredito("1234-5678-9012-3456", "12/27", banco, cliente);

        // Mostrar información de la tarjeta, cliente y banco
        System.out.println("Número de Tarjeta: " + tarjeta.getNumero());
        System.out.println("Fecha de Vencimiento: " + tarjeta.getFechaVencimiento());
        System.out.println("Banco: " + tarjeta.getBanco().getNombre());
        System.out.println("CUIT Banco: " + tarjeta.getBanco().getCuit());
        System.out.println("Cliente: " + tarjeta.getCliente().getNombre());
        System.out.println("DNI Cliente: " + tarjeta.getCliente().getDni());
    }
}
