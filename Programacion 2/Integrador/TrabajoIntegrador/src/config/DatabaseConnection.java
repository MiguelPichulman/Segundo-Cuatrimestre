
package config;//GESTIONA LA CONEXION CON LA BASE DE DATOS MySQL

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/*
Clase utilitaria que centraliza los parametros de conexion
(URL, usuario, contraseña) y proporciona conexiones a la aplicacion
*/
public class DatabaseConnection {
   
    private static final String URL = System.getProperty("db.url", "jdbc:mysql://localhost:3306/trabajofinal");
    private static final String USER = System.getProperty("db.user", "root");
    private static final String PASSWORD = System.getProperty("db.password", "root");
    
    
    /*
    Bloque que carga el Driver JDBC de MySQL
    */
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
    /*
    Constructor privado
    */
    private DatabaseConnection() {
        throw new UnsupportedOperationException("Esta es una clase utilitaria y no debe ser instanciada");
    }
    
    /*
    Metodo estatico que utiliza JDBC para abrir y devolver una nueva conexion a la base de datos.
    */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    /*
    Metodo que verifica los parametros de conexion
    */
    private static void validateConfiguration() {
        if (URL == null || URL.trim().isEmpty()) {
            throw new IllegalStateException("La URL de la base de datos no esta configurada");
        }
        if (USER == null || USER.trim().isEmpty()) {
            throw new IllegalStateException("El usuario de la base de datos no esta configurado");
        }
        // PASSWORD puede ser vacío (común en MySQL local con usuario root sin contraseña)
        // Solo validamos que no sea null
        if (PASSWORD == null) {
            throw new IllegalStateException("La contraseña de la base de datos no esta configurada");
        }
    }
}
