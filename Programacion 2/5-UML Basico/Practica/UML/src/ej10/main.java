/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej10;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Titular titular = new Titular("Martin Gomez", "38945612");

        // Crear una cuenta bancaria con clave seguridad y titular
        CuentaBancaria cuenta = new CuentaBancaria(
            "0123456789012345678901",
            15000.75,
            "ABC1234XYZ",
            "2025-09-26",
            titular
        );

        // Mostrar informacion de la cuenta bancaria
        System.out.println("CBU: " + cuenta.getCbu());
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println("Codigo clave de seguridad: " + cuenta.getClaveSeguridad().getCodigo());
        System.out.println("Ultima modificacion clave: " + cuenta.getClaveSeguridad().getUltimaModificacion());
        System.out.println("Titular: " + cuenta.getTitular().getNombre());
        System.out.println("DNI titular: " + cuenta.getTitular().getDni());
    }
}
