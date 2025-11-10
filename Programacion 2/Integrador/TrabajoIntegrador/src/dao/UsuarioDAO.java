package dao;

import entities.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO para la entidad Usuario usando JDBC y PreparedStatement.
 */
public class UsuarioDAO implements GenericDao<Usuario> {

    private static final String INSERT =
            "INSERT INTO usuario (username, email, activo, fechaRegistro, id, eliminado) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BY_ID =
            "SELECT * FROM usuario WHERE id = ? AND eliminado = FALSE";

    private static final String SELECT_ALL =
            "SELECT * FROM usuario WHERE eliminado = FALSE";

    private static final String UPDATE =
            "UPDATE usuario SET username = ?, email = ?, activo = ?, fechaRegistro = ? " +
            "WHERE id = ? AND eliminado = FALSE";

    private static final String DELETE_LOGICO =
            "UPDATE usuario SET eliminado = TRUE WHERE id = ?";

    @Override
    public Usuario crear(Usuario usuario, Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getEmail());
            stmt.setBoolean(3, usuario.isActivo());
            stmt.setTimestamp(4, Timestamp.valueOf(usuario.getFechaRegistro()));
            //stmt.setTimestamp(4, Timestamp.valueOf(usuario.getFechaRegistro()));
            stmt.setInt(5, usuario.getCredencial_id().getId());
            stmt.setBoolean(6, false);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        }
        return usuario;
    }

    @Override
    public Usuario leer(long id, Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Usuario> leerTodos(Connection connection) throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        }
        return usuarios;
    }

    @Override
    public Usuario actualizar(Usuario usuario, Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getEmail());
            stmt.setBoolean(3, usuario.isActivo());
            stmt.setTimestamp(4, Timestamp.valueOf(usuario.getFechaRegistro()));
            stmt.setInt(5, usuario.getId());

            stmt.executeUpdate();
        }
        return usuario;
    }

    @Override
    public void eliminar(long id, Connection connection) throws Exception {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_LOGICO)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    // Método auxiliar para mapear ResultSet a Usuario
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setEmail(rs.getString("email"));
        usuario.setActivo(rs.getBoolean("activo"));
        usuario.setFechaRegistro(rs.getTimestamp("fechaRegistro").toLocalDateTime());
        //usuario.setFechaRegistro(rs.getTimestamp("fechaRegistro").toLocalDateTime());
        // Podés cargar la credencial con otro DAO si lo necesitás
        return usuario;
    }
}

   