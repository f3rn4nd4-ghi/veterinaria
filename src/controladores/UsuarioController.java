package controladores;

import conexion.Conexion;
import modelos.usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gestionar operaciones sobre usuarios.
 * Incluye métodos para insertar y listar usuarios.
 */
public class UsuarioController {

    // Método para insertar un usuario
    public boolean insertarUsuario(usuario usuario) {
        String sql = "INSERT INTO usuario (usuario, contrasenia, nombre, apellido, telefono, direccion) "
                   + "VALUES (?, MD5(?), ?, ?, ?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, usuario.getUsuario());
            pst.setString(2, usuario.getContrasenia());
            pst.setString(3, usuario.getNombre());
            pst.setString(4, usuario.getApellido());
            pst.setString(5, usuario.getTelefono());
            pst.setString(6, usuario.getDireccion());

            pst.executeUpdate();
            System.out.println("✅ Usuario insertado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // Método para listar todos los usuarios
    public List<usuario> listarUsuarios() {
        List<usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = Conexion.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                usuario usuario = new usuario();
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasenia(rs.getString("contrasenia")); // Hasheada
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));

                lista.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener usuarios: " + e.getMessage());
        }

        return lista;
    }
}
