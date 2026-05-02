package com.restaurante.util;

import java.util.Scanner;


public class EntradaUsuario {

    private static final Scanner scanner = new Scanner(System.in);

    private EntradaUsuario() {}

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠  Introduce un número entero válido.");
            }
        }
    }

    public static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  ⚠  Introduce un número decimal válido (ej: 12.50).");
            }
        }
    }

    public static boolean confirmar(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String resp = scanner.nextLine().trim().toLowerCase();
        return resp.equals("s") || resp.equals("si") || resp.equals("sí");
    }

    public static void pausar() {
        System.out.print("\nPulsa ENTER para continuar...");
        scanner.nextLine();
    }

    public static void cerrar() {
        scanner.close();
    }
}
