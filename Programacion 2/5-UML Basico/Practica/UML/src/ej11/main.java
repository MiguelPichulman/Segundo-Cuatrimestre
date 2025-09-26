/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej11;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Artista artista = new Artista("The Beatles", "Rock");

        // Crear una cancion con artista
        Cancion cancion = new Cancion("Hey Jude", artista);

        // Crear un reproductor
        Reproductor reproductor = new Reproductor();

        // Reproducir la cancion
        reproductor.reproducir(cancion);
    }
}
