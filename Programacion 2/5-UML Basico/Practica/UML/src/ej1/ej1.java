package ej1;

import java.util.Date;

public class ej1 {
    public static void main(String[] args) {
        
        Titular titular = new Titular("Juan Pérez", "12345678");

        
        Pasaporte pasaporte = new Pasaporte(
            "A1234567",
            new Date(),
            "fotoDeJuan.jpg",
            "JPEG",
            titular
        );

        // Mostrar información del pasaporte y titular
        System.out.println("Número de pasaporte: " + pasaporte.getNumero());
        System.out.println("Fecha de emisión: " + pasaporte.getFechaEmision());
        System.out.println("Foto: " + pasaporte.getFoto().getImagen() + ", Formato: " + pasaporte.getFoto().getFormato());
        System.out.println("Titular: " + pasaporte.getTitular().getNombre());
        System.out.println("DNI del titular: " + pasaporte.getTitular().getDni());
    }
}
