/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static final String URL = "jdbc:mysql://ballast.proxy.rlwy.net:38970/veterinaria";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "swyiNBHYXVjnRyUIAkhXoUNZUcAhuCdD";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver de MySQL
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }
    public static void main(String[] args) {
        Connection conexion = Conexion.conectar();
        
        if (conexion != null) {
            System.out.println(" Conexión exitosa a la base de datos.");
        } else {
            System.out.println(" Error en la conexión.");
        }
    }
}
