/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej13;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Laura Diaz", "laura.diaz@email.com");

        // Crear un generador de QR
        GeneradorQR generador = new GeneradorQR();

        // Generar codigo QR con valor y usuario
        generador.generar("https://tup.sied.utn.edu.ar", usuario);
    }
}
