package com.poo.proyecto.managers;

import com.poo.proyecto.models.Ruta;
import java.io.*;
import java.util.*;

public class RutaManager {

    public static void crearRuta(Ruta ruta) {
        String archivo = "src/main/java/com/poo/proyecto/resources/rutas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(ruta.toFileString());
            writer.newLine();
            System.out.println("Ruta registrada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar ruta: " + e.getMessage());
        }
    }

    public static boolean existeRuta(String codigoRuta) {
        String archivo = "src/main/java/com/poo/proyecto/resources/rutas.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(codigoRuta + "|")) {
                    return true;
                }
            }
        } catch (IOException e) {
            // no pasa nada si aún no existe
        }
        return false;
    }

    public static boolean verificarPaquetesExistentes(List<String> codigosPaquetes) {
        String archivo = "src/main/java/com/poo/proyecto/resources/paquetes.txt";
        Set<String> paquetesRegistrados = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length > 0) {
                    paquetesRegistrados.add(partes[0]);
                }
            }
        } catch (IOException e) {
            return false;
        }

        return paquetesRegistrados.containsAll(codigosPaquetes);
    }
}
