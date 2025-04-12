package controladores;

import conexion.Conexion;
import modelos.usuario;
import java.sql.*;

public class AuthController {

    public static boolean autenticar(usuario u) {
        try (Connection conn = Conexion.conectar()) {
            if (conn == null) {
                System.out.println("No se pudo conectar a la base de datos.");
                return false;
            }

            String sql = "SELECT * FROM usuario WHERE usuario = ? AND contrasenia = MD5(?)";

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, u.getUsuario());
                pst.setString(2, u.getContrasenia());

                ResultSet rs = pst.executeQuery();
                return rs.next(); // true si encontró el usuario
            }

        } catch (SQLException e) {
            System.out.println("Error al autenticar: " + e.getMessage());
            return false;
        }
    }
}
