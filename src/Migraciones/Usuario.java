/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Migraciones;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.Connection;

import java.util.Random;

/**
 *
 * @author FERNANDA
 */
public class Usuario {

    public void usuario_Table() {
        try (Connection conn = Conexion.conectar()) {
            if (conn == null) {
                System.out.println("No se puede conectar a la base de datos en este momento.");
                return;
            }

            // Eliminar tabla si existe
            String sqlDropTable = "DROP TABLE IF EXISTS usuario";
            conn.createStatement().execute(sqlDropTable);
            System.out.println("Tabla 'usuario' eliminada.");

            // Crear tabla con nuevos campos
            String sqlTable = "CREATE TABLE IF NOT EXISTS usuario ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "usuario VARCHAR(50) NOT NULL UNIQUE, "
                    + "contrasenia VARCHAR(255) NOT NULL, "
                    + "nombre VARCHAR(255) NOT NULL, "
                    + "apellido VARCHAR(255) NOT NULL, "
                    + "telefono VARCHAR(255)NOT NULL ,"
                    + "direccion VARCHAR(255)NOT NULL"
                    + ");";
            conn.createStatement().execute(sqlTable);
            System.out.println("Tabla 'usuario' creada correctamente.");

            // Insertar usuario admin
            String sqlInsertAdmin = "INSERT INTO usuario "
                    + "(usuario, contrasenia, nombre, apellido, telefono, direccion) "
                    + "VALUES ('admin', MD5('admin'), 'Admin', 'Principal', '0000000000', 'Sistema Central')";
            conn.createStatement().execute(sqlInsertAdmin);
            System.out.println("Usuario 'admin' insertado.");

            // Usuarios realistas
            String[][] usuarios = {
                {"camila.moran", "Camila2024", "Camila", "Moran", "3124567890", "Cra 45 #10-20"},
                {"juan.perez", "JPerez#2023", "Juan", "Perez", "3019876543", "Cl 12 #34-56"},
                {"laura.gomez", "LGomez!88", "Laura", "Gomez", "3102233445", "Av Siempre Viva 742"},
                {"mario.rosales", "Mario*789", "Mario", "Rosales", "3157788990", "Cl 100 #20-30"},
                {"ana.vega", "AnaV1234", "Ana", "Vega", "3205544332", "Cra 33 #45-67"}
            };

            String insertUsuario = "INSERT INTO usuario (usuario, contrasenia, nombre, apellido, telefono, direccion) "
                    + "VALUES (?, MD5(?), ?, ?, ?, ?)";

            try (PreparedStatement pst = conn.prepareStatement(insertUsuario)) {
                for (String[] u : usuarios) {
                    pst.setString(1, u[0]);
                    pst.setString(2, u[1]);
                    pst.setString(3, u[2]);
                    pst.setString(4, u[3]);
                    pst.setString(5, u[4]);
                    pst.setString(6, u[5]);
                    pst.executeUpdate();
                    System.out.println("Usuario insertado: " + u[0]);
                }
            }

            // Usuario aleatorio
            String[] nombres = {"Sofia", "Lucas", "Valentina", "Andres", "Mariana"};
            String[] apellidos = {"Lopez", "Ramirez", "Castro", "Torres", "Medina"};
            String[] calles = {"Cl 10", "Cra 8", "Av 68", "Cl 100", "Cra 33"};

            Random rand = new Random();
            String nombreRand = nombres[rand.nextInt(nombres.length)];
            String apellidoRand = apellidos[rand.nextInt(apellidos.length)];
            int num = rand.nextInt(1000);
            String usuarioRand = nombreRand.toLowerCase() + "." + apellidoRand.toLowerCase() + num;
            String passRand = nombreRand.substring(0, 1).toUpperCase() + apellidoRand + "@" + (2000 + rand.nextInt(25));
            String telefonoRand = "3" + (rand.nextInt(900000000) + 100000000);
            String direccionRand = calles[rand.nextInt(calles.length)] + " #" + (10 + rand.nextInt(90)) + "-" + (1 + rand.nextInt(50));

            try (PreparedStatement pst = conn.prepareStatement(insertUsuario)) {
                pst.setString(1, usuarioRand);
                pst.setString(2, passRand);
                pst.setString(3, nombreRand);
                pst.setString(4, apellidoRand);
                pst.setString(5, telefonoRand);
                pst.setString(6, direccionRand);
                pst.executeUpdate();
                System.out.println("Usuario aleatorio insertado: " + usuarioRand + " / " + passRand);
            }

        } catch (Exception e) {
            System.out.println("Error en la migración: " + e.getMessage());
        }
    }
}  