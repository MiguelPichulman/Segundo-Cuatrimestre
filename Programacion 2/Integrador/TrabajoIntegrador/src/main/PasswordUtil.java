package main;

/**
 * Clase utilitaria para simular la generación de hash y salt.
 */
public class PasswordUtil {

    /**
     * Simula la generacion de un "hash" a partir de una contraseña.
     * Oculta la contraseña real
     */
    public static String simularHash(String password, String salt) {
        // Simulacion simple: concatena y añade un sufijo.
        return (password + salt) + "_hashed";
    }

    /**
     * Simula la generacion de un "salt".
     */
    public static String simularSalt() {
        // Devuelve un valor fijo para la simulacion.
        return "mi_salt_secreto_" + System.currentTimeMillis();
    }
}
