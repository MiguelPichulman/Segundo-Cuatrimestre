package dao;

import config.DatabaseConnection;
import entities.CredencialAcceso;
import entities.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Implementacion concreta del DAO para la entidad Usuario
Contiene el SQL especifico para la tabla usuario.
*/
public class UsuarioDAO implements GenericDAO<Usuario> {
    //Constantes
    private static final String INSERT =
        "INSERT INTO usuario (eliminado, username, email, activo, fechaRegistro, credencial_id) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BY_ID =
        "SELECT u.*, " +
        "c.id AS c_id, c.eliminado AS c_eliminado, c.hashPassword AS c_hashPassword, " +
        "c.salt AS c_salt, c.ultimoCambio AS c_ultimoCambio, c.requiereReset AS c_requiereReset " +
        "FROM usuario u " +
        "LEFT JOIN CredencialAcceso c ON u.credencial_id = c.id " +
        "WHERE u.id = ? AND u.eliminado = FALSE";

    private static final String SELECT_ALL =
        "SELECT u.*, " +
        "c.id AS c_id, c.eliminado AS c_eliminado, c.hashPassword AS c_hashPassword, " +
        "c.salt AS c_salt, c.ultimoCambio AS c_ultimoCambio, c.requiereReset AS c_requiereReset " +
        "FROM usuario u " +
        "LEFT JOIN CredencialAcceso c ON u.credencial_id = c.id " +
        "WHERE u.eliminado = FALSE";

    private static final String UPDATE =
        "UPDATE usuario SET username = ?, email = ?, activo = ?, fechaRegistro = ?, credencial_id = ? " +
        "WHERE id = ? AND eliminado = FALSE";

    private static final String DELETE_LOGICO =
        "UPDATE usuario SET eliminado = TRUE WHERE id = ?";
    
    // SQL para la nueva búsqueda específica
    private static final String SELECT_BY_USERNAME =
        "SELECT u.*, " +
        "c.id AS c_id, c.eliminado AS c_eliminado, c.hashPassword AS c_hashPassword, " +
        "c.salt AS c_salt, c.ultimoCambio AS c_ultimoCambio, c.requiereReset AS c_requiereReset " +
        "FROM usuario u " +
        "LEFT JOIN CredencialAcceso c ON u.credencial_id = c.id " +
        "WHERE u.username = ? AND u.eliminado = FALSE";   
    
    
    public UsuarioDAO() {
    }

    /**
     * CREA UN USUARIO, ABRE UNA CONEXION Y LA CIERRA
     */
    @Override
    public void crear(Usuario usuario, Connection conn) throws Exception {
  
    try (PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
        
        setUsuarioParameters(stmt, usuario);
        stmt.executeUpdate();
        setGeneratedId(stmt, usuario);
        
    } catch (SQLException e) {
       
        throw new Exception("Error en DAO al crear el usuario: " + e.getMessage(), e);
    }
}
    /*
    Metodo helper para asignar parametros de un objeto Usuario
    a un PreparedStatement, evitando codigo duplicado.
    */
    private void setUsuarioParameters(PreparedStatement stmt, Usuario usuario) throws SQLException{
        
        stmt.setBoolean(1, usuario.isEliminado());
        stmt.setString(2, usuario.getUsername());
        stmt.setString(3, usuario.getEmail());
        stmt.setBoolean(4, usuario.isActivo());
        if (usuario.getFechaRegistro() != null) {
            stmt.setTimestamp(5, Timestamp.valueOf(usuario.getFechaRegistro()));
        } else {
            stmt.setNull(5, Types.TIMESTAMP);
        }
        
        
        if (usuario.getCredencialId() != null) {
            stmt.setLong(6, usuario.getCredencialId().getId());
        } else {
           
            stmt.setNull(6, Types.BIGINT); 
        }        
    }
    
    /*
    Metodo Helper para obtener el ID autogenerado por la BD despues de un INSERT
    */
    private void setGeneratedId(PreparedStatement stmt, Usuario usuario) throws SQLException{
   
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                usuario.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("La insercion del usuario fallo, no se obtuvo ID generado");
            }
        }
    }    

    /**
     * Busca un Usuario por su ID...
     */
    @Override
    public Usuario leer(long id, Connection conn) throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuarioCompleto(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error en DAO al leer el usuario: " + e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * Metodo que convierte una fila del ResultSet (incluyendo el JOIN con CredencialAcceso)
     * en un objeto Usuario.
     */
    private Usuario mapearUsuarioCompleto(ResultSet rs) throws SQLException {
        
        Usuario usuario = new Usuario();
        
        usuario.setId(rs.getLong("id"));
        usuario.setEliminado(rs.getBoolean("eliminado"));
        usuario.setUsername(rs.getString("username"));
        usuario.setEmail(rs.getString("email"));
        usuario.setActivo(rs.getBoolean("activo"));
        
        Timestamp fechaRegistroTs = rs.getTimestamp("fechaRegistro");
        if (fechaRegistroTs != null) {
            usuario.setFechaRegistro(fechaRegistroTs.toLocalDateTime());
        }

        long credencialId = rs.getLong("c_id");
        if (credencialId > 0 && !rs.wasNull()) {
            
            CredencialAcceso credencial = new CredencialAcceso();
            credencial.setId(credencialId);
            credencial.setEliminado(rs.getBoolean("c_eliminado"));
            credencial.setHashPassword(rs.getString("c_hashPassword"));
            credencial.setSalt(rs.getString("c_salt"));
            
            Timestamp ultimoCambioTs = rs.getTimestamp("c_ultimoCambio");
            if (ultimoCambioTs != null) {
                credencial.setUltimoCambio(ultimoCambioTs.toLocalDateTime());
            }
            credencial.setRequiereReset(rs.getBoolean("c_requiereReset"));
            
            usuario.setCredencialId(credencial);
        }
        
        return usuario;
    }
  
    /**
     * Lee todos los Usuarios
     */
    @Override
    public List<Usuario> leerTodos(Connection conn) throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                usuarios.add(mapearUsuarioCompleto(rs));
            }
        } catch (SQLException e) {
            throw new Exception("Error en DAO al leer todos los usuarios: " + e.getMessage(), e);
        }
        return usuarios;
    }
    
    /**
     * Actualiza un Usuario existente
     */
    @Override
    public void actualizar(Usuario usuario, Connection conn) throws Exception {
        if (usuario == null || usuario.getId() == 0) {
            throw new IllegalArgumentException("El usuario a actualizar no puede ser nulo o no tener un ID válido.");
        }
        
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
            setUpdateParameters(stmt, usuario);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La actualización del usuario falló, ID: " + usuario.getId() + " no encontrado o ya eliminado.");
            }
        } catch (SQLException e) {
            throw new Exception("Error en DAO al actualizar el usuario: " + e.getMessage(), e);
        }
    }
    
    /**
     * Método helper para establecer los parametros de un UPDATE...
     */
    private void setUpdateParameters(PreparedStatement stmt, Usuario usuario) throws SQLException {
        
        int index = 1;
        
        stmt.setString(index++, usuario.getUsername());
        stmt.setString(index++, usuario.getEmail());
        stmt.setBoolean(index++, usuario.isActivo());
        
        if (usuario.getFechaRegistro() != null) {
            stmt.setTimestamp(index++, Timestamp.valueOf(usuario.getFechaRegistro()));
        } else {
            stmt.setNull(index++, Types.TIMESTAMP);
        }
        
        if (usuario.getCredencialId() != null) {
            stmt.setLong(index++, usuario.getCredencialId().getId());
        } else {
            stmt.setNull(index++, Types.BIGINT);
        }
        
        stmt.setLong(index++, usuario.getId());
    }
    
    /**
     * Realiza una baja logica
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
                throw new SQLException("La eliminacion (logica) del usuario fallo, ID: " + id + " no encontrado");
            }
        } catch (SQLException e) {
            throw new Exception("Error en DAO al eliminar logicamente el usuario: " + e.getMessage(), e);
        }
    }
    /**
     * Busca un Usuario por su username (busqueda especifica requerida)
     */
    public Usuario buscarPorUsername(String username, Connection conn) throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_BY_USERNAME)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Reutilizamos el helper de mapeo que ya teníamos
                    return mapearUsuarioCompleto(rs);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error en DAO al buscar por username: " + e.getMessage(), e);
        }
        return null;
    }
}