package com.poo.proyecto.managers;

import com.poo.proyecto.models.Ruta;
import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;
import java.util.ArrayList;  // Asegúrate de importar la clase Ruta
import java.util.List;

public class ReporteRutaManager {

    public static void generarReporteRuta(String codigoRuta) {
        // Obtener las rutas desde el manager
        List<Ruta> rutas = RutaManager.listarRutas();
        List<String> eventos = obtenerEventos(codigoRuta);
        List<String> rastreo = obtenerRastreo(codigoRuta);

        long entregasExitosas = 0;
        long entregasFallidas = 0;
        long tiempoTotalRuta = 0;

        // Lógica para contar entregas exitosas, fallidas y calcular el tiempo total en ruta.
        // Puedes añadir la lógica específica de cómo manejar los eventos y rastreo para obtener estas métricas.

        System.out.println("Reporte de la Ruta: " + codigoRuta);
        System.out.println("Entregas Exitosas: " + entregasExitosas);
        System.out.println("Entregas Fallidas: " + entregasFallidas);
        System.out.println("Tiempo Total en Ruta: " + tiempoTotalRuta + " segundos");
        System.out.println("Eventos Registrados:");
        eventos.forEach(System.out::println);
        System.out.println("Rastreo:");
        rastreo.forEach(System.out::println);

        ValidadorUtils.esperar(2000);
    }

    private static List<String> obtenerEventos(String codigoRuta) {
        List<String> eventos = new ArrayList<>();
        String archivoEventos = "src/main/java/com/poo/proyecto/resources/eventosRuta.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoEventos))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(codigoRuta + "|")) {
                    eventos.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer eventos: " + e.getMessage());
        }
        return eventos;
    }

    private static List<String> obtenerRastreo(String codigoRuta) {
        List<String> rastreo = new ArrayList<>();
        String archivoRastreo = "src/main/java/com/poo/proyecto/resources/rastreo.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoRastreo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(codigoRuta + "|")) {
                    rastreo.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer rastreo: " + e.getMessage());
        }
        return rastreo;
    }
}
