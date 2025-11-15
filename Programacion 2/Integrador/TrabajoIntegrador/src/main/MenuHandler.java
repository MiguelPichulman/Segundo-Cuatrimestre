package main;

import entities.CredencialAcceso;
import entities.Usuario;
import service.CredencialAccesoService;
import service.UsuarioService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador de las operaciones del menu (Menu Handler).
 * Gestiona la logica de interaccion con el usuario y llama a los servicios.
 */
public class MenuHandler {

    private final Scanner scanner;
    private final UsuarioService usuarioService;
    private final CredencialAccesoService credencialService;

    public MenuHandler(Scanner scanner, UsuarioService usuarioService, CredencialAccesoService credencialService) {
        this.scanner = scanner;
        this.usuarioService = usuarioService;
        this.credencialService = credencialService;
    }

    /**
     * Opcion 1: Crear un Usuario (A) y su Credencial (B)
     */
    public void crearUsuario() {
        try {
            System.out.println("--- Creando Nuevo Usuario ---");
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Email: ");
            String email = scanner.nextLine().trim().toLowerCase();

            System.out.print("Contraseña: ");
            String password = scanner.nextLine().trim();

            //Simular Hashing (En un TFI, esto es suficiente)
            String salt = PasswordUtil.simularSalt();
            String hash = PasswordUtil.simularHash(password, salt);

            //Crear Entidad B (Credencial)
            CredencialAcceso credencial = new CredencialAcceso();
            credencial.setHashPassword(hash);
            credencial.setSalt(salt);
            credencial.setRequiereReset(false);
            credencial.setUltimoCambio(LocalDateTime.now());

            //Crear Entidad A (Usuario)
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            usuario.setEmail(email);
            usuario.setActivo(true);
            usuario.setFechaRegistro(LocalDateTime.now());
            usuario.setCredencialId(credencial); // Asocia B con A

            //Llamar al servicio transaccional
            usuarioService.insertar(usuario);

            //Mensaje claro de exito
            System.out.println("Exito: Usuario '" + usuario.getUsername() + "' creado con ID: " + usuario.getId());
            System.out.println("   (Credencial asociada con ID: " + credencial.getId() + ")");

        } catch (IllegalArgumentException e) {
            // Error de validación
            System.err.println("❌ Error de Validacion: " + e.getMessage());
        } catch (Exception e) {
            // Error de BD (ej. Unicidad)
            System.err.println("❌ Error de Base de Datos: " + e.getMessage());
        }
    }

    /**
     * Opcion 2: Listar todos los Usuarios (A) y su Credencial (B).
     */
    public void listarUsuarios() {
        try {
            System.out.println("--- Listando Todos los Usuarios ---");
            List<Usuario> usuarios = usuarioService.getAll();
            if (usuarios.isEmpty()) {
                System.out.println("No se encontraron usuarios.");
                return;
            }

            for (Usuario u : usuarios) {
                System.out.println("--------------------");
                System.out.println("ID: " + u.getId() + " | Username: " + u.getUsername());
                System.out.println("Email: " + u.getEmail() + " | Activo: " + u.isActivo());
                if (u.getCredencialId() != null) {
                    System.out.println("   -> Credencial ID: " + u.getCredencialId().getId() + 
                                       " | Requiere Reset: " + u.getCredencialId().isRequiereReset());
                } else {
                    System.out.println("   -> (Sin credencial asociada)");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
    }

    /**
     * Opcion 3: Actualizar datos de un Usuario (A).
     */
    public void actualizarUsuario() {
        try {
            System.out.print("Ingrese el ID del usuario a actualizar: ");
            long id = Long.parseLong(scanner.nextLine().trim());

            Usuario usuario = usuarioService.getById(id);
            if (usuario == null) {
                // Manejo de ID inexistente
                System.err.println("Error: No se encontro un usuario con ID: " + id);
                return;
            }

            System.out.println("Actualizando Usuario: " + usuario.getUsername());
            System.out.print("Nuevo Username (Actual: " + usuario.getUsername() + ") (Deje vacio para no cambiar): ");
            String username = scanner.nextLine().trim();
            if (!username.isEmpty()) {
                usuario.setUsername(username);
            }

            System.out.print("Nuevo Email (Actual: " + usuario.getEmail() + ") (Deje vacio para no cambiar): ");
            String email = scanner.nextLine().trim().toLowerCase();
            if (!email.isEmpty()) {
                usuario.setEmail(email);
            }

            System.out.print("¿Esta Activo? (s/n) (Actual: " + usuario.isActivo() + ") (Deje vacio para no cambiar): ");
            String activoInput = scanner.nextLine().trim().toUpperCase(); //
            if (!activoInput.isEmpty()) {
                usuario.setActivo(activoInput.equals("S"));
            }

            usuarioService.actualizar(usuario);
            System.out.println("Exito: Usuario actualizado");

        } catch (NumberFormatException e) {
            System.err.println("Error: ID invalido. Debe ser un numero"); //
        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage()); //
        }
    }

    /**
     * Opcion 4: Eliminar logicamente un Usuario (A) y su Credencial (B).
     */
    public void eliminarUsuario() {
        try {
            System.out.print("Ingrese el ID del usuario a eliminar: ");
            long id = Long.parseLong(scanner.nextLine().trim());

            System.out.print("¿Esta seguro que desea eliminar al usuario con ID " + id + "? (s/n): ");
            String confirm = scanner.nextLine().trim().toUpperCase();

            if (confirm.equals("S")) {
                usuarioService.eliminar(id);
                System.out.println("Exito: Usuario con ID " + id + " eliminado logicamente");
            } else {
                System.out.println("Operacion cancelada");
            }

        } catch (NumberFormatException e) {
            System.err.println("Error: ID invalido, debe ser un numero");
        } catch (Exception e) {
            // Captura el error "ID no encontrado" lanzado desde el Service
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    /**
     * Opcion 5: Buscar un Usuario por su ID.
     */
    public void buscarUsuarioPorId() {
        try {
            System.out.print("Ingrese el ID del usuario a buscar: ");
            long id = Long.parseLong(scanner.nextLine().trim());
            Usuario u = usuarioService.getById(id);

            if (u == null) {
                System.err.println("Error: No se encontro un usuario con ID: " + id);
                return;
            }

            System.out.println("--- Usuario Encontrado ---");
            System.out.println("ID: " + u.getId() + " | Username: " + u.getUsername());
            System.out.println("Email: " + u.getEmail() + " | Activo: " + u.isActivo());
            System.out.println("Fecha Registro: " + u.getFechaRegistro());
            if (u.getCredencialId() != null) {
                System.out.println("   -> Credencial ID: " + u.getCredencialId().getId() + 
                                   " | Ultimo Cambio: " + u.getCredencialId().getUltimoCambio());
            }

        } catch (NumberFormatException e) {
            System.err.println("Error: ID invalido. Debe ser un numero.");
        } catch (Exception e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
    }

    /**
     * Opcion 6: Búsqueda especifica por Username
     * (Busqueda por campo relevante)
     */
    public void buscarUsuarioPorUsername() {
        try {
            System.out.print("Ingrese el Username del usuario a buscar: ");
            String username = scanner.nextLine().trim();
            Usuario u = usuarioService.getByUsername(username);

            if (u == null) {
                System.err.println("Error: No se encontro un usuario con Username: '" + username + "'");
                return;
            }

            System.out.println("--- Usuario Encontrado ---");
            System.out.println("ID: " + u.getId() + " | Username: " + u.getUsername());
            System.out.println("Email: " + u.getEmail() + " | Activo: " + u.isActivo());

        } catch (Exception e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
    }

    /**
     * Opcion 7: Actualizar la Contraseña (CRUD de B - Update).
     */
    public void actualizarContrasena() {
        try {
            System.out.print("Ingrese el ID del usuario cuya contraseña desea cambiar: ");
            long id = Long.parseLong(scanner.nextLine().trim());

            Usuario usuario = usuarioService.getById(id);
            if (usuario == null || usuario.getCredencialId() == null) {
                System.err.println("Error: No se encontro el usuario o no tiene credencial asociada");
                return;
            }
            
            CredencialAcceso credencial = usuario.getCredencialId();

            System.out.print("Ingrese la NUEVA contraseña para " + usuario.getUsername() + ": ");
            String newPassword = scanner.nextLine().trim();

            // Simular Hashing
            String newSalt = PasswordUtil.simularSalt();
            String newHash = PasswordUtil.simularHash(newPassword, newSalt);
            
            // Actualizar la entidad B
            credencial.setHashPassword(newHash);
            credencial.setSalt(newSalt);
            credencial.setUltimoCambio(LocalDateTime.now());
            credencial.setRequiereReset(false); // Asumimos que el cambio la resetea

            // Llamar al servicio de B
            credencialService.actualizar(credencial);

            System.out.println("Exito: Contraseña actualizada para el usuario ID: " + id);

        } catch (NumberFormatException e) {
            System.err.println("Error: ID invalido. Debe ser un numero"); 
        } catch (Exception e) {
            System.err.println("Error al actualizar la contraseña: " + e.getMessage());
        }
    }
}