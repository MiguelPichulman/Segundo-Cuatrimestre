package dao;

import entities.CredencialAcceso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion del DAO para CredencialAcceso
 */
public class CredencialAccesoDAO implements GenericDAO<CredencialAcceso> {

    // Constantes
    private static final String INSERT =
            "INSERT INTO CredencialAcceso (hashPassword, salt, ultimoCambio, requiereReset, eliminado) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_BY_ID =
            "SELECT * FROM CredencialAcceso WHERE id = ? AND eliminado = FALSE";

    private static final String SELECT_ALL =
            "SELECT * FROM CredencialAcceso WHERE eliminado = FALSE";

    private static final String UPDATE =
            "UPDATE CredencialAcceso SET hashPassword = ?, salt = ?, ultimoCambio = ?, requiereReset = ? " +
            "WHERE id = ? AND eliminado = FALSE";

    private static final String DELETE_LOGICO =
            "UPDATE CredencialAcceso SET eliminado = TRUE WHERE id = ?";

    /**
     * Constructor vacio
     */
    public CredencialAccesoDAO() {
    }

    /**
     * CREA una CredencialAcceso, abriendo y cerrando su propia conexion
     * El ID sera generado por la base de datos
     */
    @Override
    public void crear(CredencialAcceso credencial, Connection conn) throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            setCrearParameters(stmt, credencial);
            stmt.executeUpdate();
            setGeneratedId(stmt, credencial);
        } catch (SQLException e) {
            throw new Exception("Error en DAO al crear la credencial: " + e.getMessage(), e);
        }
    }

    /**
     * LEE una CredencialAcceso por ID, abriendo y cerrando su propia conexion
     */
    @Override
    public CredencialAcceso leer(long id, Connection conn) throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearCredencialAcceso(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error en DAO al leer la credencial: " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * LEE TODAS las CredencialAcceso (activas), abriendo y cerrando su propia conexion
     */
    @Override
    public List<CredencialAcceso> leerTodos(Connection conn) throws Exception {
        List<CredencialAcceso> credenciales = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                credenciales.add(mapearCredencialAcceso(rs));
            }
        } catch (SQLException e) {
            throw new Exception("Error en DAO al leer todas las credenciales: " + e.getMessage(), e);
        }
        return credenciales;
    }

    /**
     * ACTUALIZA una CredencialAcceso, abriendo y cerrando su propia conexion
     */
    @Override
    public void actualizar(CredencialAcceso credencial, Connection conn) throws Exception {
        if (credencial == null || credencial.getId() == 0) {
            throw new IllegalArgumentException("La credencial a actualizar no puede ser nula o no tener un ID válido.");
        }
        
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
            setUpdateParameters(stmt, credencial);
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                 throw new SQLException("Actualización de CredencialAcceso falló, ID: " + credencial.getId() + " no encontrado o ya eliminado.");
            }
        } catch (SQLException e) {
            throw new Exception("Error en DAO al actualizar la credencial: " + e.getMessage(), e);
        }
    }

    /**
     * ELIMINA (logicamente) una CredencialAcceso, abriendo y cerrando su propia conexion
     */
    @Override
    public void eliminar(long id, Connection conn) throws Exception {
        if (id == 0) {
            throw new IllegalArgumentException("El ID a eliminar no puede ser 0.");
        }
        
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_LOGICO)) {
            stmt.setLong(1, id);
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                 throw new SQLException("Eliminación lógica de CredencialAcceso falló, ID: " + id + " no encontrado.");
            }
        } catch (SQLException e) {
            throw new Exception("Error en DAO al eliminar lógicamente la credencial: " + e.getMessage(), e);
        }
    }

    // --- METODOS HELPER (Iguales al patron de UsuarioDAO) ---

    /**
     * Metodo helper para establecer los parametros de un INSERT
     */
    private void setCrearParameters(PreparedStatement stmt, CredencialAcceso credencial) throws SQLException {
        int index = 1;
        stmt.setString(index++, credencial.getHashPassword());
        stmt.setString(index++, credencial.getSalt());
        
        if (credencial.getUltimoCambio() != null) {
            stmt.setTimestamp(index++, Timestamp.valueOf(credencial.getUltimoCambio()));
        } else {
            stmt.setNull(index++, Types.TIMESTAMP);
        }
        
        stmt.setBoolean(index++, credencial.isRequiereReset());
        stmt.setBoolean(index++, credencial.isEliminado()); // Baja lógica inicial
    }
    
    /**
     * Metodo helper para establecer los parametros de un UPDATE
     */
    private void setUpdateParameters(PreparedStatement stmt, CredencialAcceso credencial) throws SQLException {
        int index = 1;
        stmt.setString(index++, credencial.getHashPassword());
        stmt.setString(index++, credencial.getSalt());
        
        if (credencial.getUltimoCambio() != null) {
            stmt.setTimestamp(index++, Timestamp.valueOf(credencial.getUltimoCambio()));
        } else {
            stmt.setNull(index++, Types.TIMESTAMP);
        }
        
        stmt.setBoolean(index++, credencial.isRequiereReset());
        
        // --- Parametro del WHERE ---
        stmt.setLong(index++, credencial.getId());
    }

    /**
     * Establece el ID autogenerado en el objeto CredencialAcceso
     */
    private void setGeneratedId(PreparedStatement stmt, CredencialAcceso credencial) throws SQLException {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                credencial.setId(rs.getLong(1));
            } else {
                throw new SQLException("La inserción de la credencial falló, no se obtuvo ID generado.");
            }
        }
    }
    
    /**
     * Metodo auxiliar para mapear un ResultSet a un objeto CredencialAcceso
     */
    private CredencialAcceso mapearCredencialAcceso(ResultSet rs) throws SQLException {
        CredencialAcceso credencial = new CredencialAcceso();
        
        credencial.setId(rs.getLong("id"));
        credencial.setEliminado(rs.getBoolean("eliminado"));
        credencial.setHashPassword(rs.getString("hashPassword"));
        credencial.setSalt(rs.getString("salt"));
        
        Timestamp ultimoCambioTs = rs.getTimestamp("ultimoCambio");
        if (ultimoCambioTs != null) {
             credencial.setUltimoCambio(ultimoCambioTs.toLocalDateTime());
        }
       
        credencial.setRequiereReset(rs.getBoolean("requiereReset"));
        
        return credencial;
    }
}