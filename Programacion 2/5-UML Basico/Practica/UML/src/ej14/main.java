/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej14;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Proyecto proyecto = new Proyecto("Documental Naturaleza", 120);

        // Crear un editor de video
        EditorVideo editor = new EditorVideo();

        // Exportar proyecto en formato mp4
        editor.exportar("mp4", proyecto);
    }
}
