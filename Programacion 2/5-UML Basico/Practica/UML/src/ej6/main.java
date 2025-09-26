/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej6;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("María Fernández", "555-1234");

        // Crear una mesa
        Mesa mesa = new Mesa(10, 4);

        // Crear una reserva con fecha, hora, cliente y mesa
        Reserva reserva = new Reserva("2025-10-01", "20:30", cliente, mesa);

        // Mostrar información de la reserva
        System.out.println("Fecha de reserva: " + reserva.getFecha());
        System.out.println("Hora de reserva: " + reserva.getHora());
        System.out.println("Cliente: " + reserva.getCliente().getNombre());
        System.out.println("Teléfono cliente: " + reserva.getCliente().getTelefono());
        System.out.println("Mesa número: " + reserva.getMesa().getNumero());
        System.out.println("Capacidad de mesa: " + reserva.getMesa().getCapacidad());
    }
}
