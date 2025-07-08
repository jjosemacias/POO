package com.poo.proyecto.managers;

import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;
import java.util.*;

public class HistorialManager {

    public static void consultarHistorial(String placaVehiculo) {
        String archivoRastreo = "src/main/java/com/poo/proyecto/resources/rastreo.txt";
        String archivoEventos = "src/main/java/com/poo/proyecto/resources/eventosRuta.txt";
        String archivoRutas = "src/main/java/com/poo/proyecto/resources/rutas.txt";

        // Leer los archivos y obtener el historial
        List<String> historialRastreo = obtenerHistorial(placaVehiculo, archivoRastreo);
        List<String> historialEventos = obtenerHistorial(placaVehiculo, archivoEventos);
        List<String> historialRutas = obtenerHistorial(placaVehiculo, archivoRutas);

        // Mostrar el historial del vehículo
        System.out.println("Historial de Vehículo: " + placaVehiculo);
        System.out.println("Rastreo:");
        historialRastreo.forEach(System.out::println);
        System.out.println("Eventos:");
        historialEventos.forEach(System.out::println);
        System.out.println("Rutas:");
        historialRutas.forEach(System.out::println);

        ValidadorUtils.esperar(2000);
    }

    private static List<String> obtenerHistorial(String placa, String archivo) {
        List<String> historial = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains(placa)) {
                    historial.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return historial;
    }
}
