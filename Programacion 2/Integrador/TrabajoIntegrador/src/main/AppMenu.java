package main;

import service.CredencialAccesoService;
import service.UsuarioService;
import java.util.Scanner;

/**
 * Gestor principal del menu.
 * Gestiona el ciclo de vida del menu, inicializa servicios y el Scanner.
 */
public class AppMenu {

    private final Scanner scanner;
    private final MenuHandler menuHandler;
    private boolean running;

    /**
     * Constructor que inicializa la aplicacion.
     * Crea el Scanner, los Servicios y el Handler.
     */
    public AppMenu() {
        this.scanner = new Scanner(System.in);
        
        //Inicializa los servicios
        UsuarioService usuarioService = new UsuarioService();
        CredencialAccesoService credencialService = new CredencialAccesoService();
        
        //Las instancias se pasan al Handler para que pueda usarlas
        this.menuHandler = new MenuHandler(scanner, usuarioService, credencialService);
        this.running = true;
    }

    /**
     * Bucle principal del menu.
     */
    public void run() {
        while (running) {
            try {
                MenuDisplay.mostrarMenuPrincipal();
                // Lee la línea completa para evitar problemas de buffer
                String input = scanner.nextLine(); 
                
                // Entrada de usuario limpia y  ejecuta la accion seleccionada
                int opcion = Integer.parseInt(input.trim());
                processOption(opcion);
                
            } catch (NumberFormatException e) {
                // Manejo robusto de entradas invalidas (no numericas)
                // Entradas invalidas)
                System.err.println("Error: Entrada invalida. Por favor, ingrese solo numeros");
            }
        }
        // Cierra el scanner al salir
        scanner.close();
        System.out.println("Aplicacion finalizada");
    }

    /**
     * Procesa la opción seleccionada y delega al MenuHandler.
     */
    private void processOption(int opcion) {
        switch (opcion) {
            case 1 -> menuHandler.crearUsuario();
            case 2 -> menuHandler.listarUsuarios();
            case 3 -> menuHandler.actualizarUsuario();
            case 4 -> menuHandler.eliminarUsuario();
            case 5 -> menuHandler.buscarUsuarioPorId();
            case 6 -> menuHandler.buscarUsuarioPorUsername();
            case 7 -> menuHandler.actualizarContrasena();
            case 0 -> {
                System.out.println("Saliendo...");
                running = false;
            }
            default -> System.err.println("Opción no válida. Intente de nuevo.");
        }
    }
}