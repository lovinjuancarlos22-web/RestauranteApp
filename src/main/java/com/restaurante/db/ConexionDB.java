package com.restaurante.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL      = "jdbc:postgresql://localhost:5432/restaurante";
    private static final String USUARIO  = "postgres";
    private static final String PASSWORD = "1"; // Cambia por tu contraseña

    private static Connection instancia = null;


    private ConexionDB() {}


    public static Connection getConexion() throws SQLException {
        if (instancia == null || instancia.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                instancia = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("[DB] Conexión establecida correctamente.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver PostgreSQL no encontrado: " + e.getMessage());
            }
        }
        return instancia;
    }


    public static void cerrarConexion() {
        if (instancia != null) {
            try {
                if (!instancia.isClosed()) {
                    instancia.close();
                    System.out.println("[DB] Conexión cerrada.");
                }
            } catch (SQLException e) {
                System.err.println("[DB] Error al cerrar: " + e.getMessage());
            }
        }
    }
}
