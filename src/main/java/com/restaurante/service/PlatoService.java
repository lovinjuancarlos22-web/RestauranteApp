package com.restaurante.service;

import com.restaurante.db.ConexionDB;
import com.restaurante.model.Plato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlatoService {

    public List<Plato> listarMenu() {
        List<Plato> lista = new ArrayList<>();
        String sql = """
                SELECT p.*, c.nombre AS nombre_categoria
                FROM plato p
                JOIN categoria c ON p.id_categoria = c.id_categoria
                WHERE p.disponible = TRUE
                ORDER BY c.nombre, p.precio
                """;
        try (Statement st = ConexionDB.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapearFila(rs));
        } catch (SQLException e) {
            System.err.println("[PlatoService] Error al listar menú: " + e.getMessage());
        }
        return lista;
    }

    public List<Plato> listarTodos() {
        List<Plato> lista = new ArrayList<>();
        String sql = """
                SELECT p.*, c.nombre AS nombre_categoria
                FROM plato p
                JOIN categoria c ON p.id_categoria = c.id_categoria
                ORDER BY c.nombre, p.nombre
                """;
        try (Statement st = ConexionDB.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapearFila(rs));
        } catch (SQLException e) {
            System.err.println("[PlatoService] Error al listar todos: " + e.getMessage());
        }
        return lista;
    }

    public Plato buscarPorId(int id) {
        String sql = """
                SELECT p.*, c.nombre AS nombre_categoria
                FROM plato p JOIN categoria c ON p.id_categoria = c.id_categoria
                WHERE p.id_plato = ?
                """;
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearFila(rs);
            }
        } catch (SQLException e) {
            System.err.println("[PlatoService] Error al buscar: " + e.getMessage());
        }
        return null;
    }

    public boolean insertar(Plato plato) {
        String sql = "INSERT INTO plato (id_categoria, nombre, descripcion, precio, disponible) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, plato.getIdCategoria());
            ps.setString(2, plato.getNombre());
            ps.setString(3, plato.getDescripcion());
            ps.setDouble(4, plato.getPrecio());
            ps.setBoolean(5, plato.isDisponible());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[PlatoService] Error al insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean toggleDisponibilidad(int idPlato) {
        String sql = "UPDATE plato SET disponible = NOT disponible WHERE id_plato = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, idPlato);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[PlatoService] Error al cambiar disponibilidad: " + e.getMessage());
            return false;
        }
    }

    private Plato mapearFila(ResultSet rs) throws SQLException {
        return new Plato(
                rs.getInt("id_plato"),
                rs.getInt("id_categoria"),
                rs.getString("nombre_categoria"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getDouble("precio"),
                rs.getBoolean("disponible")
        );
    }
}
