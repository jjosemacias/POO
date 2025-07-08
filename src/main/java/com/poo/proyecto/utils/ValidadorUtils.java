package com.poo.proyecto.utils;

import java.util.Set;

public class ValidadorUtils {

    private static final Set<String> LICENCIAS_VALIDAS = Set.of(
        "A", "A1", "B", "C1", "C", "D1", "D", "E1", "E", "F", "G"
    );

    public static boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("\\d{10}");
    }

    public static boolean esCedulaValida(String cedula) {
        return cedula != null && cedula.matches("\\d{10}");
    }

    public static boolean esPlacaValida(String placa) {
        return placa != null && placa.matches("[A-Z]{3}-\\d{4}");
    }

    public static boolean esLicenciaValida(String licencia) {
        return licencia != null && LICENCIAS_VALIDAS.contains(licencia.toUpperCase());
    }
}
