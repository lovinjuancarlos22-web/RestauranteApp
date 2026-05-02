package com.restaurante.controller;

import com.restaurante.model.Cliente;
import com.restaurante.service.ClienteService;
import com.restaurante.util.EntradaUsuario;

import java.util.List;


public class ClienteController {

    private final ClienteService service = new ClienteService();

    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║     GESTIÓN DE CLIENTES    ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║  1. Listar todos           ║");
            System.out.println("║  2. Buscar por nombre      ║");
            System.out.println("║  3. Añadir cliente         ║");
            System.out.println("║  4. Modificar cliente      ║");
            System.out.println("║  5. Eliminar cliente       ║");
            System.out.println("║  0. Volver                 ║");
            System.out.println("╚════════════════════════════╝");

            int opcion = EntradaUsuario.leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> listarTodos();
                case 2 -> buscarPorNombre();
                case 3 -> añadirCliente();
                case 4 -> modificarCliente();
                case 5 -> eliminarCliente();
                case 0 -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void listarTodos() {
        List<Cliente> clientes = service.listarTodos();
        System.out.println("\n--- LISTADO DE CLIENTES (" + clientes.size() + ") ---");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            clientes.forEach(System.out::println);
        }
        EntradaUsuario.pausar();
    }

    private void buscarPorNombre() {
        String texto = EntradaUsuario.leerTexto("Nombre o apellido a buscar: ");
        List<Cliente> resultado = service.buscarPorNombre(texto);
        System.out.println("\n--- RESULTADOS (" + resultado.size() + ") ---");
        resultado.forEach(System.out::println);
        EntradaUsuario.pausar();
    }

    private void añadirCliente() {
        System.out.println("\n--- NUEVO CLIENTE ---");
        String nombre    = EntradaUsuario.leerTexto("Nombre: ");
        String apellido  = EntradaUsuario.leerTexto("Apellido: ");
        String telefono  = EntradaUsuario.leerTexto("Teléfono: ");
        String email     = EntradaUsuario.leerTexto("Email: ");

        Cliente c = new Cliente(nombre, apellido, telefono, email);
        if (service.insertar(c)) {
            System.out.println("✔ Cliente añadido correctamente.");
        } else {
            System.out.println("✘ Error al añadir el cliente.");
        }
        EntradaUsuario.pausar();
    }

    private void modificarCliente() {
        int id = EntradaUsuario.leerEntero("ID del cliente a modificar: ");
        Cliente c = service.buscarPorId(id);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        System.out.println("Cliente actual: " + c);
        String nombre   = EntradaUsuario.leerTexto("Nuevo nombre (" + c.getNombre() + "): ");
        String apellido = EntradaUsuario.leerTexto("Nuevo apellido (" + c.getApellido() + "): ");
        String telefono = EntradaUsuario.leerTexto("Nuevo teléfono (" + c.getTelefono() + "): ");
        String email    = EntradaUsuario.leerTexto("Nuevo email (" + c.getEmail() + "): ");

        if (!nombre.isBlank())   c.setNombre(nombre);
        if (!apellido.isBlank()) c.setApellido(apellido);
        if (!telefono.isBlank()) c.setTelefono(telefono);
        if (!email.isBlank())    c.setEmail(email);

        if (service.actualizar(c)) {
            System.out.println("✔ Cliente actualizado.");
        } else {
            System.out.println("✘ Error al actualizar.");
        }
        EntradaUsuario.pausar();
    }

    private void eliminarCliente() {
        int id = EntradaUsuario.leerEntero("ID del cliente a eliminar: ");
        Cliente c = service.buscarPorId(id);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        System.out.println("Cliente: " + c);
        if (EntradaUsuario.confirmar("¿Seguro que quieres eliminarlo?")) {
            if (service.eliminar(id)) {
                System.out.println("✔ Cliente eliminado.");
            } else {
                System.out.println("✘ No se pudo eliminar (puede tener reservas asociadas).");
            }
        }
        EntradaUsuario.pausar();
    }
}
