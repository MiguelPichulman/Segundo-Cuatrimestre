/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej8;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Ana Lopez", "ana.lopez@email.com");

        // Crear un documento con firma digital y usuario
        Documento documento = new Documento(
            "Contrato de servicio",
            "Contenido detallado del contrato...",
            "ABC123XYZ456",
            "2025-09-26",
            usuario
        );

        // Mostrar informacion del documento y firma digital
        System.out.println("Titulo del documento: " + documento.getTitulo());
        System.out.println("Contenido: " + documento.getContenido());
        System.out.println("Codigo Hash de la firma: " + documento.getFirmaDigital().getCodigoHash());
        System.out.println("Fecha de la firma: " + documento.getFirmaDigital().getFecha());
        System.out.println("Usuario que firmo: " + documento.getFirmaDigital().getUsuario().getNombre());
        System.out.println("Email del usuario: " + documento.getFirmaDigital().getUsuario().getEmail());
    }
}
