package com.restaurante.service;

import com.restaurante.db.ConexionDB;
import com.restaurante.model.Mesa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MesaService {

    public List<Mesa> listarTodas() {
        List<Mesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM mesa ORDER BY numero_mesa";
        try (Statement st = ConexionDB.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapearFila(rs));
        } catch (SQLException e) {
            System.err.println("[MesaService] Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public List<Mesa> listarLibres() {
        List<Mesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM mesa WHERE estado = 'libre' ORDER BY capacidad";
        try (Statement st = ConexionDB.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(mapearFila(rs));
        } catch (SQLException e) {
            System.err.println("[MesaService] Error al listar libres: " + e.getMessage());
        }
        return lista;
    }

    public List<Mesa> listarLibresConCapacidad(int personas) {
        List<Mesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM mesa WHERE estado = 'libre' AND capacidad >= ? ORDER BY capacidad";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, personas);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            System.err.println("[MesaService] Error al buscar por capacidad: " + e.getMessage());
        }
        return lista;
    }

    public boolean cambiarEstado(int idMesa, String nuevoEstado) {
        String sql = "UPDATE mesa SET estado = ? WHERE id_mesa = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idMesa);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[MesaService] Error al cambiar estado: " + e.getMessage());
            return false;
        }
    }

    public Mesa buscarPorId(int id) {
        String sql = "SELECT * FROM mesa WHERE id_mesa = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearFila(rs);
            }
        } catch (SQLException e) {
            System.err.println("[MesaService] Error al buscar: " + e.getMessage());
        }
        return null;
    }

    private Mesa mapearFila(ResultSet rs) throws SQLException {
        return new Mesa(
                rs.getInt("id_mesa"),
                rs.getInt("numero_mesa"),
                rs.getInt("capacidad"),
                rs.getString("estado")
        );
    }
}
