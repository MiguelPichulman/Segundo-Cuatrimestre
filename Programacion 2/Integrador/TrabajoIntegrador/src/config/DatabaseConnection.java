
package config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseConnection {
    
    //CONFIGURACION
    //
    //ACA DEBEN PONER SUS USUARIOS Y CONTRASEÑAS, EN MI CASO LA DE WORKBENCH
    //
    private static final String URL = System.getProperty("db.url", "jdbc:mysql://localhost:3306/trabajofinal");
    private static final String USER = System.getProperty("db.user", "root");
    private static final String PASSWORD = System.getProperty("db.password", "root");
    
    static {
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");

            // Valida configuración tempranamente (fail-fast)
            validateConfiguration();
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Error: No se encontró el driver JDBC de MySQL: " + e.getMessage());
        } catch (IllegalStateException e) {
            throw new ExceptionInInitializerError("Error en la configuración de la base de datos: " + e.getMessage());
        }
    }
    
    private DatabaseConnection() {
        throw new UnsupportedOperationException("Esta es una clase utilitaria y no debe ser instanciada");
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    private static void validateConfiguration() {
        if (URL == null || URL.trim().isEmpty()) {
            throw new IllegalStateException("La URL de la base de datos no está configurada");
        }
        if (USER == null || USER.trim().isEmpty()) {
            throw new IllegalStateException("El usuario de la base de datos no está configurado");
        }
        // PASSWORD puede ser vacío (común en MySQL local con usuario root sin contraseña)
        // Solo validamos que no sea null
        if (PASSWORD == null) {
            throw new IllegalStateException("La contraseña de la base de datos no está configurada");
        }
    }
    
    //LAS SIGUIENTES LINEAS SON DE PRUEBA.LO DEJO PORQUE ES PARA QUE TAMBIEN PRUEBEN SU CONEXION. NO FORMA PARTE DEL  TRABAJO
    //PRUEBEN SIN EXECUTAR XAMPP Y LUEGO EJECUTANDO XAMPP
    /**
     * Cierra de forma segura un objeto Connection.
     * ESTE ES EL MÉTODO QUE FALTABA PARA PODER REALIZAR LA PRUEBA DE CONEXION
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Advertencia: Error al intentar cerrar la conexión: " + e.getMessage());
            }
        }
    }
   
    
    // --- MÉTODO MAIN PARA LA PRUEBA DE CONEXIÓN ---
    public static void main(String[] args) {
        Connection testConnection = null;
        System.out.println("--- Iniciando Prueba de Conexión JDBC ---");
        System.out.println("URL configurada: " + URL);
        System.out.println("Usuario: " + USER);

        try {
            // 1. Intentar obtener la conexión
            testConnection = getConnection();
            
            // 2. Verificar el estado de la conexión
            if (testConnection != null && !testConnection.isClosed()) {
                System.out.println("✅ ¡Conexión exitosa a la base de datos!");
                System.out.println("Nombre de la Base de Datos: " + testConnection.getCatalog());
            } else {
                System.out.println("❌ La conexión no fue exitosa o se cerró prematuramente.");
            }
            
        } catch (SQLException e) {
            // 3. Manejar errores específicos de SQL
            System.err.println("❌ Error de conexión con la base de datos.");
            System.err.println("Por favor, verifica el estado del servidor MySQL y las credenciales.");
            System.err.println("Mensaje: " + e.getMessage());
            
        } catch (ExceptionInInitializerError e) {
             // 4. Manejar errores del static block (driver no encontrado o config inválida)
            System.err.println("❌ Error fatal al inicializar la clase DatabaseConnection.");
            System.err.println("Causa: " + e.getCause().getMessage());
        }
        finally {
            // 5. Asegurar el cierre de la conexión (Ahora que el método existe, el error desaparece)
            closeConnection(testConnection);
            System.out.println("--- Prueba de Conexión Finalizada ---");
        }
    }
}
