package com.poo.proyecto.utils;

import java.util.Set;

public class ValidadorUtils {

    private static final Set<String> LICENCIAS_VALIDAS = Set.of(
        "A", "A1", "B", "C1", "C", "D1", "D", "E1", "E", "F", "G"
    );

    public static boolean esCorreoValido(String correo) {
        if (correo == null || !correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("El correo no es válido.");
            return false;
        }
        return true;
    }

    public static boolean esTelefonoValido(String telefono) {
        if (telefono == null || !telefono.matches("\\d{10}")) {
            System.out.println("El teléfono debe tener 10 dígitos.");
            return false;
        }
        return true;
    }

    public static boolean esCedulaValida(String cedula) {
        if (cedula.length() != 10 || !cedula.matches("\\d+")) {
            System.out.println("La cédula debe tener 10 dígitos numéricos.");
            return false;
        }
        return true;
    }

    public static boolean esPlacaValida(String placa) {
        if (placa == null || !placa.matches("[A-Z]{3}-\\d{4}")) {
            System.out.println("La placa debe tener el formato 'ABC-1234'.");
            return false;
        }
        return true;
    }

    public static boolean esLicenciaValida(String licencia) {
        if (!LICENCIAS_VALIDAS.contains(licencia)) {
            System.out.println("Licencia no válida. Las opciones válidas son: A, A1, B, C1, C, D1, D, E1, E, F, G.");
            return false;
        }
        return true;
    }

    public static boolean esCadenaNoVacia(String cadena) {
        if (cadena == null || cadena.trim().isEmpty()) {
            System.out.println("El campo no puede estar vacío.");
            return false;
        }
        return true;
    }

    public static boolean esNumeroPositivo(double numero) {
        if (numero <= 0) {
            System.out.println("El número debe ser positivo.");
            return false;
        }
        return true;
    }

    public static boolean esEstadoOperativoValido(String estadoOperativo) {
        if (estadoOperativo == null || !(estadoOperativo.equalsIgnoreCase("Operativo") || estadoOperativo.equalsIgnoreCase("Inoperativo"))) {
            System.out.println("El estado operativo debe ser 'Operativo' o 'Inoperativo'.");
            return false;
        }
        return true;
    }

    public static boolean esFechaValida(String fecha) {
        if (fecha == null || !fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println("La fecha debe tener el formato 'dd/mm/yyyy'.");
            return false;
        }
        return true;
    }

    // Método para dormir/pausar
    public static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println("Error al esperar: " + e.getMessage());
        }
    }
}
