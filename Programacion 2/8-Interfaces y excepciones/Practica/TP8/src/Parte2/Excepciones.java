
package Parte2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Excepciones {
    public static void main(String[] args) {
        //División segura
        divisionSegura(10, 0);
        divisionSegura(10, 2);

        //Conversión de cadena a número
        conversionCadenaANumero("42");
        conversionCadenaANumero("texto");

        //Lectura de archivo
        leeArchivo("archivo_que_no_existe.txt");
        leeArchivo("archivo.txt");

        //Edad inválida
        verificarEdad(25);
        verificarEdad(-5);

        //Try-with-resources
        leerArchivoTryWithResources("archivo.txt");
    }

    public static void divisionSegura(int a, int b) {
        try {
            int res = a / b;
            System.out.println("Resultado: " + res);
        } catch (ArithmeticException e) {
            System.out.println("Division por cero");
        }
    }

    public static void conversionCadenaANumero(String texto) {
        try {
            int numero = Integer.parseInt(texto);
            System.out.println("Convertido: " + numero);
        } catch (NumberFormatException e) {
            System.out.println("No es un numero valido");
        }
    }

    public static void leeArchivo(String nombre) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombre));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
    }

    public static void verificarEdad(int edad) {
        try {
            if (edad < 0 || edad > 120)
                throw new EdadInvalidaException("Edad fuera de rango permitido");
            System.out.println("Edad valida: " + edad);
        } catch (EdadInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void leerArchivoTryWithResources(String nombre) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
