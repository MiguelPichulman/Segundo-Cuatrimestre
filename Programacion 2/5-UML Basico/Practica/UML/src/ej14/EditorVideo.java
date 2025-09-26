/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej14;

/**
 *
 * @author migue
 */
public class EditorVideo {
    public void exportar(String formato, Proyecto proyecto) {
        Render render = new Render(formato, proyecto);
        // Simular proceso de exportacion
        System.out.println("Exportando proyecto: " + proyecto.getNombre());
        System.out.println("Duracion: " + proyecto.getDuracionMin() + " min");
        System.out.println("Formato exportado: " + render.getFormato());
    }
}
