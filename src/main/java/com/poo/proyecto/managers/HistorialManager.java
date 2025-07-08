package com.poo.proyecto.managers;

import java.io.*;
import java.util.*;

public class HistorialManager {

    public static void consultarHistorialVehiculo(String placa) {
        String rutasFile = "src/main/java/com/poo/proyecto/resources/rutas.txt";
        String eventosFile = "src/main/java/com/poo/proyecto/resources/eventosRuta.txt";
        String historialFile = "src/main/java/com/poo/proyecto/resources/historialVehiculos.txt";

        List<String> rutas = new ArrayList<>();
        List<String> eventos = new ArrayList<>();
        int entregas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutasFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(placa)) {
                    rutas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo rutas.txt: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(eventosFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(placa)) {
                    eventos.add(linea);
                    if (linea.contains("Entrega realizada")) {
                        entregas++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo eventosRuta.txt: " + e.getMessage());
        }

        // Mostrar resultados en consola
        System.out.println("\n--- Historial del Vehículo: " + placa + " ---");
        System.out.println("Rutas:");
        rutas.forEach(System.out::println);

        System.out.println("\nEventos:");
        eventos.forEach(System.out::println);

        System.out.println("\nTotal de entregas realizadas: " + entregas);

        // Escribir respaldo en historialVehiculos.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(historialFile, true))) {
            bw.write("Vehículo: " + placa + "\n");
            bw.write("Rutas:\n");
            for (String r : rutas) bw.write(r + "\n");
            bw.write("Eventos:\n");
            for (String ev : eventos) bw.write(ev + "\n");
            bw.write("Total entregas: " + entregas + "\n\n");
        } catch (IOException e) {
            System.out.println("Error escribiendo historial: " + e.getMessage());
        }
    }
}
