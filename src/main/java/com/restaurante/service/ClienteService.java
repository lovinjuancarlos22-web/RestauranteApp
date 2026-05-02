package com.restaurante.service;

import com.restaurante.db.ConexionDB;
import com.restaurante.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteService {


    public boolean insertar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, apellido, telefono, email) VALUES (?,?,?,?)";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("[ClienteService] Error al insertar: " + e.getMessage());
            return false;
        }
    }


    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY apellido, nombre";
        try (Statement st = ConexionDB.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            System.err.println("[ClienteService] Error al listar: " + e.getMessage());
        }
        return lista;
    }


    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapearFila(rs);
            }
        } catch (SQLException e) {
            System.err.println("[ClienteService] Error al buscar: " + e.getMessage());
        }
        return null;
    }


    public List<Cliente> buscarPorNombre(String texto) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE LOWER(nombre) LIKE ? OR LOWER(apellido) LIKE ? ORDER BY apellido";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            String patron = "%" + texto.toLowerCase() + "%";
            ps.setString(1, patron);
            ps.setString(2, patron);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapearFila(rs));
            }
        } catch (SQLException e) {
            System.err.println("[ClienteService] Error al buscar por nombre: " + e.getMessage());
        }
        return lista;
    }


    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre=?, apellido=?, telefono=?, email=? WHERE id_cliente=?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setInt(5, cliente.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[ClienteService] Error al actualizar: " + e.getMessage());
            return false;
        }
    }


    public boolean eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[ClienteService] Error al eliminar: " + e.getMessage());
            return false;
        }
    }


    private Cliente mapearFila(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id_cliente"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("telefono"),
                rs.getString("email")
        );
    }
}
