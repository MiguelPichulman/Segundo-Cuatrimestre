/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej11;

/**
 *
 * @author migue
 */
public class Reproductor {
    public void reproducir(Cancion cancion) {
        // Simula reproducir la cancion
        System.out.println("Reproduciendo: " + cancion.getTitulo());
        System.out.println("Artista: " + cancion.getArtista().getNombre());
        System.out.println("Genero: " + cancion.getArtista().getGenero());
    }
}
