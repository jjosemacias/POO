package com.poo.proyecto.managers;

import java.io.*;
import java.util.*;

public class ReporteRutaManager {

    public static void generarReporteRuta(String codigoRuta) {
        String rutasFile = "src/main/java/com/poo/proyecto/resources/rutas.txt";
        String eventosFile = "src/main/java/com/poo/proyecto/resources/eventosRuta.txt";
        String reporteFile = "src/main/java/com/poo/proyecto/resources/reportes.txt";

        int entregasExitosas = 0;
        int entregasFallidas = 0; // Esto se puede ajustar si hay eventos de fallas
        int totalEventos = 0;
        String conductor = "Desconocido";
        // Buscar detalles de la ruta
        try (BufferedReader br = new BufferedReader(new FileReader(rutasFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(codigoRuta)) {
                    String[] partes = linea.split("\\|");
                    if (partes.length >= 3) {
                        conductor = partes[2].trim();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo rutas.txt: " + e.getMessage());
        }

        // Buscar eventos de la ruta
        List<String> eventos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(eventosFile))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(codigoRuta)) {
                    eventos.add(linea);
                    totalEventos++;
                    if (linea.contains("Entrega realizada")) {
                        entregasExitosas++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo eventosRuta.txt: " + e.getMessage());
        }

        // Mostrar por consola
        System.out.println("\n--- Reporte de Ruta: " + codigoRuta + " ---");
        System.out.println("Conductor: " + conductor);
        System.out.println("Entregas exitosas: " + entregasExitosas);
        System.out.println("Entregas fallidas: " + entregasFallidas); // Puedes ajustar si se detectan fallos
        System.out.println("Eventos registrados: " + totalEventos);
        eventos.forEach(System.out::println);

        // Guardar en archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(reporteFile, true))) {
            bw.write("Reporte Ruta: " + codigoRuta + "\n");
            bw.write("Conductor: " + conductor + "\n");
            bw.write("Entregas exitosas: " + entregasExitosas + "\n");
            bw.write("Entregas fallidas: " + entregasFallidas + "\n");
            bw.write("Eventos registrados: " + totalEventos + "\n");
            for (String ev : eventos) {
                bw.write(ev + "\n");
            }
            bw.write("\n");
        } catch (IOException e) {
            System.out.println("Error escribiendo el reporte: " + e.getMessage());
        }
    }
}
