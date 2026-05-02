package com.restaurante;

import com.restaurante.controller.ClienteController;
import com.restaurante.controller.MesaPlatoController;
import com.restaurante.db.ConexionDB;
import com.restaurante.util.EntradaUsuario;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {


        try {
            ConexionDB.getConexion();
        } catch (SQLException e) {
            System.err.println("ERROR: No se pudo conectar a la base de datos.");
            System.err.println("Comprueba que PostgreSQL está activo y los datos de conexión son correctos.");
            System.err.println("Detalle: " + e.getMessage());
            System.exit(1);
        }


        ClienteController    clienteCtrl    = new ClienteController();
        MesaPlatoController  mesaPlatoCtrl  = new MesaPlatoController();

        boolean ejecutando = true;

        while (ejecutando) {
            mostrarMenuPrincipal();
            int opcion = EntradaUsuario.leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> clienteCtrl.mostrarMenu();
                case 2 -> mesaPlatoCtrl.mostrarMenuMesas();
                case 3 -> mesaPlatoCtrl.mostrarMenuPlatos();
                case 0 -> {
                    System.out.println("\n¡Hasta luego!\n");
                    ejecutando = false;
                }
                default -> System.out.println("Opción no válida. Elige entre 0 y 3.");
            }
        }

1
        ConexionDB.cerrarConexion();
        EntradaUsuario.cerrar();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║    GESTIÓN DE RESTAURANTE v1.0   ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  1. Clientes                     ║");
        System.out.println("║  2. Mesas                        ║");
        System.out.println("║  3. Carta / Menú                 ║");
        System.out.println("║  0. Salir                        ║");
        System.out.println("╚══════════════════════════════════╝");
    }
}
