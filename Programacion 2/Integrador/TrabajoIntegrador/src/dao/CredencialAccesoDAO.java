package dao;

import entities.CredencialAcceso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO para la entidad CredencialAcceso usando JDBC y PreparedStatement.
 * NOTA: Esta clase NO maneja la clave foránea 'usuario_id' requerida para la relación 1:1.
 * Dicha lógica debe ser manejada por la Capa Service.
 */
public class CredencialAccesoDAO implements GenericDao<CredencialAcceso> {

    // Constantes SQL
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

    @Override
    public CredencialAcceso crear(CredencialAcceso credencial, Connection connection) throws Exception {
        // Se utiliza try-with-resources para asegurar que el PreparedStatement se cierre automáticamente
        try (PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            // Mapeo de parámetros
            int index = 1;
            stmt.setString(index++, credencial.getHashPassword());
            stmt.setString(index++, credencial.getSalt());
            
            // Conversión de java.time.LocalDateTime a java.sql.Timestamp
            if (credencial.getUltimoCambio() != null) {
                stmt.setTimestamp(index++, Timestamp.valueOf(credencial.getUltimoCambio()));
            } else {
                stmt.setNull(index++, Types.TIMESTAMP);
            }
            
            stmt.setBoolean(index++, credencial.isRequiereReset());
            stmt.setBoolean(index++, credencial.isEliminado()); // Baja lógica inicial
            
            stmt.executeUpdate();

            // Obtener el ID generado por la base de datos
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    credencial.setId(rs.getInt(1));
                } else {
                    throw new SQLException("Creación de CredencialAcceso falló, no se obtuvo el ID generado.");
                }
            }
        }
        return credencial; // Retorna la entidad con el ID asignado
    }

    @Override
    public CredencialAcceso leer(long id, Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearCredencialAcceso(rs);
                }
            }
        }
        return null; // Retorna null si no existe o tiene baja lógica
    }

    @Override
    public List<CredencialAcceso> leerTodos(Connection connection) throws Exception {
        List<CredencialAcceso> credenciales = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                credenciales.add(mapearCredencialAcceso(rs));
            }
        }
        return credenciales;
    }

    @Override
    public CredencialAcceso actualizar(CredencialAcceso credencial, Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
            
            // Mapeo de parámetros
            int index = 1;
            stmt.setString(index++, credencial.getHashPassword());
            stmt.setString(index++, credencial.getSalt());
            
            if (credencial.getUltimoCambio() != null) {
                stmt.setTimestamp(index++, Timestamp.valueOf(credencial.getUltimoCambio()));
            } else {
                stmt.setNull(index++, Types.TIMESTAMP);
            }
            
            stmt.setBoolean(index++, credencial.isRequiereReset());
            
            // Clave del WHERE
            stmt.setInt(index++, credencial.getId());

            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                 throw new SQLException("Actualización de CredencialAcceso falló, ID: " + credencial.getId() + " no encontrado o con baja lógica.");
            }
        }
        return credencial; // Retorna la entidad actualizada
    }

    @Override
    public void eliminar(long id, Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_LOGICO)) {
            stmt.setLong(1, id);
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                 throw new SQLException("Eliminación lógica de CredencialAcceso falló, ID: " + id + " no encontrado.");
            }
        }
    }

    /**
     * Método auxiliar para mapear un ResultSet a un objeto CredencialAcceso.
     * @param rs El ResultSet de la consulta.
     * @return La entidad CredencialAcceso.
     * @throws SQLException 
     */
    private CredencialAcceso mapearCredencialAcceso(ResultSet rs) throws SQLException {
        CredencialAcceso credencial = new CredencialAcceso();
        
        credencial.setId(rs.getInt("id"));
        credencial.setEliminado(rs.getBoolean("eliminado"));
        credencial.setHashPassword(rs.getString("hashPassword"));
        credencial.setSalt(rs.getString("salt"));
        
        // Conversión de java.sql.Timestamp a java.time.LocalDateTime
        Timestamp ultimoCambioTs = rs.getTimestamp("ultimoCambio");
        if (ultimoCambioTs != null) {
             credencial.setUltimoCambio(ultimoCambioTs.toLocalDateTime());
        }
       
        credencial.setRequiereReset(rs.getBoolean("requiereReset"));
        
        // NOTA: El campo 'usuario_id' (FK) NO se mapea a la entidad 
        // ya que la relación es unidireccional (Usuario -> CredencialAcceso).
        
        return credencial;
    }

}