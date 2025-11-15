package main;

/**
 * Clase utilitaria para mostrar el menu de la aplicacion
 * Solo muestra el menu, no lee entradas.
 */
public class MenuDisplay {

    /**
     * Muestra el menu principal con todas las opciones CRUD para A y B.
     * CRUD completo A y B
     */
    public static void mostrarMenuPrincipal() {
        System.out.println("\n========= GESTION DE USUARIOS (TFI) =========");
        System.out.println("--- CRUD Usuario ---");
        System.out.println("1. Crear Usuario (Alta Usuario y Credencial)");
        System.out.println("2. Listar Usuarios");
        System.out.println("3. Actualizar Usuario (Datos personales)");
        System.out.println("4. Eliminar Usuario (Baja logica Usuario y Credencial)");
        System.out.println("--- Busquedas ---");
        System.out.println("5. Buscar Usuario por ID");
        System.out.println("6. Buscar Usuario por Username (Busqueda especifica)"); //
        System.out.println("--- CRUD Credencial ---");
        System.out.println("7. Actualizar Contraseña (Requiere Reset)");
        System.out.println("---------------------------------------------");
        System.out.println("0. Salir");
        System.out.print("Ingrese una opcion: ");
    }
}
