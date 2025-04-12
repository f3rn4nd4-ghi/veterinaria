/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import conexion.Conexion;
import modelos.Mascotas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author FERNANDA
 */
public class MascotasController {
    public static List<Mascotas> listarMascotas() {
        List<Mascotas> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";

        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                //agregar todos los resultados a la lista
                lista.add(new Mascotas(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("especie"),
                        rs.getDouble("peso"),
                        rs.getString("raza"),
                        rs.getString("genero"),
                        rs.getString("color"),
                        rs.getBoolean("esterilizado"),
                        rs.getString("observaciones")
                ));
            }
        } catch (SQLException e) {
            System.out.println(" Error al listar mascotas: " + e.getMessage());
        }
        System.out.println(lista);
        return lista;
    }

    public static boolean eliminarMascota(int idMascota) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
