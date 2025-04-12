/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package migraciones;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author German
 */
public class Mascotas {
     public void Mascotas_table(){
         try (Connection conn = Conexion.conectar()) { // Usamos la conexión de Conexion.java
            if (conn == null) {
                System.out.println(" No se pudo conectar a la base de datos en este momento.");
                return;
            }
            //Eliminar  la tabla Usuarios par despues crearla 
            String sqlDropTable = "DROP TABLE IF EXISTS mascotas";
            conn.createStatement().execute(sqlDropTable);
            System.out.println("Tabla 'mastocas' eliminada.");
          

            String sqlTabla = "CREATE TABLE IF NOT EXISTS mascotas ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nombre VARCHAR(80) NOT NULL,"
                + "edad INT NOT NULL,"
                + "especie VARCHAR(50) NOT NULL,"
                + "peso DOUBLE NOT NULL,"
                + "raza VARCHAR(100) NOT NULL,"
                + "genero VARCHAR(10) NOT NULL,"
                + "color VARCHAR(50) NOT NULL,"
                + "esterilizado BOOLEAN NOT NULL,"
                + "observaciones TEXT"
                + ")";
            conn.createStatement().execute(sqlTabla);
            System.out.println("Tabla '' verificada o creada correctamente.");

            String sqlInsertMascota = "INSERT INTO mascotas (nombre, edad, especie, peso, raza, genero, color, esterilizado, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pst = conn.prepareStatement(sqlInsertMascota)) {
                pst.setString(1, "Luna");
                pst.setInt(2, 3);
                pst.setString(3, "Perro");
                pst.setDouble(4, 12.5);
                pst.setString(5, "Labrador");
                pst.setString(6, "Hembra");
                pst.setString(7, "Negro");
                pst.setBoolean(8, true);
                pst.setString(9, "Vacunas al día");

                pst.executeUpdate(); // Ejecutar la inserción

                // Segunda mascota
                pst.setString(1, "Tom");
                pst.setInt(2, 2);
                pst.setString(3, "Gato");
                pst.setDouble(4, 4.2);
                pst.setString(5, "Siamés");
                pst.setString(6, "Macho");
                pst.setString(7, "Blanco");
                pst.setBoolean(8, false);
                pst.setString(9, "En tratamiento");

                pst.executeUpdate();}
            System.out.println(" Mascotas aleatorio creado: ");

        } catch (SQLException e) {
            System.out.println(" Error en la migración: " + e.getMessage());
        }
    }
}
