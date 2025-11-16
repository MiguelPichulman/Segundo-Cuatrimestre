package main;//interfaz de usuario

/**
 * Punto de entrada
 * responsable de crear la instancia principal de la aplicacion
 */
public class Main {

    public static void main(String[] args) {
       
        AppMenu app = new AppMenu();
        
        // Inicia el bucle principal del menu
        app.run();
    }
}