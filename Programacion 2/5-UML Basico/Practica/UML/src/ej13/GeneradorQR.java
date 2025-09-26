/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej13;

/**
 *
 * @author migue
 */
public class GeneradorQR {
    public void generar(String valor, Usuario usuario) {
        CodigoQR codigoQR = new CodigoQR(valor, usuario);
        System.out.println("Codigo QR generado:");
        System.out.println("Valor: " + codigoQR.getValor());
        System.out.println("Usuario: " + codigoQR.getUsuario().getNombre());
        System.out.println("Email: " + codigoQR.getUsuario().getEmail());
    }
}
