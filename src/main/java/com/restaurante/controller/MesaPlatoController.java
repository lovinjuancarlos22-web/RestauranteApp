package com.restaurante.controller;

import com.restaurante.model.Mesa;
import com.restaurante.model.Plato;
import com.restaurante.service.MesaService;
import com.restaurante.service.PlatoService;
import com.restaurante.util.EntradaUsuario;

import java.util.List;


public class MesaPlatoController {

    private final MesaService  mesaService  = new MesaService();
    private final PlatoService platoService = new PlatoService();

    public void mostrarMenuMesas() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║      GESTIÓN DE MESAS      ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║  1. Ver todas las mesas    ║");
            System.out.println("║  2. Ver mesas libres       ║");
            System.out.println("║  3. Buscar por capacidad   ║");
            System.out.println("║  4. Cambiar estado de mesa ║");
            System.out.println("║  0. Volver                 ║");
            System.out.println("╚════════════════════════════╝");

            int opcion = EntradaUsuario.leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> listarMesas(mesaService.listarTodas());
                case 2 -> listarMesas(mesaService.listarLibres());
                case 3 -> buscarMesasCapacidad();
                case 4 -> cambiarEstadoMesa();
                case 0 -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void listarMesas(List<Mesa> mesas) {
        System.out.println("\n--- MESAS (" + mesas.size() + ") ---");
        if (mesas.isEmpty()) System.out.println("No hay mesas.");
        else mesas.forEach(m -> System.out.println("  " + m));
        EntradaUsuario.pausar();
    }

    private void buscarMesasCapacidad() {
        int personas = EntradaUsuario.leerEntero("Número de personas: ");
        listarMesas(mesaService.listarLibresConCapacidad(personas));
    }

    private void cambiarEstadoMesa() {
        int id = EntradaUsuario.leerEntero("ID de la mesa: ");
        Mesa m = mesaService.buscarPorId(id);
        if (m == null) { System.out.println("Mesa no encontrada."); return; }
        System.out.println("Mesa actual: " + m);
        System.out.println("  1. Libre  2. Ocupada  3. Reservada");
        int op = EntradaUsuario.leerEntero("Nuevo estado: ");
        String[] estados = {"libre", "ocupada", "reservada"};
        if (op >= 1 && op <= 3) {
            mesaService.cambiarEstado(id, estados[op - 1]);
            System.out.println("✔ Estado actualizado.");
        }
        EntradaUsuario.pausar();
    }


    public void mostrarMenuPlatos() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║        CARTA / MENÚ        ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║  1. Ver carta completa     ║");
            System.out.println("║  2. Ver todos (con estado) ║");
            System.out.println("║  3. Añadir plato           ║");
            System.out.println("║  4. Cambiar disponibilidad ║");
            System.out.println("║  0. Volver                 ║");
            System.out.println("╚════════════════════════════╝");

            int opcion = EntradaUsuario.leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> verCarta();
                case 2 -> verTodosPlatos();
                case 3 -> añadirPlato();
                case 4 -> cambiarDisponibilidad();
                case 0 -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void verCarta() {
        List<Plato> platos = platoService.listarMenu();
        System.out.println("\n--- CARTA DEL RESTAURANTE ---");
        String categoriaActual = "";
        for (Plato p : platos) {
            if (!p.getNombreCategoria().equals(categoriaActual)) {
                categoriaActual = p.getNombreCategoria();
                System.out.println("\n  ── " + categoriaActual.toUpperCase() + " ──");
            }
            System.out.printf("    [%d] %-30s  %.2f€%n", p.getIdPlato(), p.getNombre(), p.getPrecio());
        }
        EntradaUsuario.pausar();
    }

    private void verTodosPlatos() {
        platoService.listarTodos().forEach(p -> System.out.println("  " + p));
        EntradaUsuario.pausar();
    }

    private void añadirPlato() {
        System.out.println("\n--- NUEVO PLATO ---");
        int    idCat = EntradaUsuario.leerEntero("ID categoría (1=Entrantes,2=Principales,3=Postres,4=Bebidas): ");
        String nom   = EntradaUsuario.leerTexto("Nombre del plato: ");
        String desc  = EntradaUsuario.leerTexto("Descripción: ");
        double prec  = EntradaUsuario.leerDecimal("Precio (€): ");
        Plato p = new Plato(idCat, nom, desc, prec);
        if (platoService.insertar(p)) System.out.println("✔ Plato añadido.");
        else System.out.println("✘ Error al añadir.");
        EntradaUsuario.pausar();
    }

    private void cambiarDisponibilidad() {
        int id = EntradaUsuario.leerEntero("ID del plato: ");
        if (platoService.toggleDisponibilidad(id)) System.out.println("✔ Disponibilidad cambiada.");
        else System.out.println("✘ Error.");
        EntradaUsuario.pausar();
    }
}
