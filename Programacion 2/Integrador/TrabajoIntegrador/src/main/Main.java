package main;

/**
 * Punto de entrada principal de la aplicacion TFI.
 * Main invoca AppMenu
 */
public class Main {

    public static void main(String[] args) {
        // Crea una instancia de AppMenu, la cual inicializa
        // el Scanner, los Servicios y el MenuHandler.
        AppMenu app = new AppMenu();
        
        // Ejecuta el loop principal del menú.
        app.run();
    }
}