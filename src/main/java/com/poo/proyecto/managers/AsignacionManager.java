package com.poo.proyecto.managers;

import com.poo.proyecto.models.Asignacion;
import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;

public class AsignacionManager {

    public static void asignarVehiculoAConductor(Asignacion asignacion) {
        String archivoAsignaciones = "src/main/java/com/poo/proyecto/resources/asignaciones.txt";

        // Guardar la asignación
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoAsignaciones, true))) {
            writer.write(asignacion.toFileString());
            writer.newLine();
            System.out.println("Vehículo asignado al conductor exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar la asignación: " + e.getMessage());
        }
        ValidadorUtils.esperar(2000);
    }

    public static boolean existePlacaAsignada(String placa) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/poo/proyecto/resources/asignaciones.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length > 0 && partes[0].equalsIgnoreCase(placa)) {
                    System.out.println("La placa " + placa + " ya está asignada con conductor cédula " + partes[1] + ".");
                    return true; // La placa ya está asignada
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de vehículos: " + e.getMessage());
        }
        System.out.println("La placa " + placa + " no está asignada.");
        return false; // La placa no está asignada
    }
    public static boolean existeCedulaAsignada(String cedula) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/poo/proyecto/resources/asignaciones.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length > 1 && partes[1].equals(cedula)) {
                    System.out.println("La cédula " + cedula + " ya está asignada.");
                    return true; // La cédula ya está asignada
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de asignaciones: " + e.getMessage());
        }

        return false; // La cédula no está asignada
    }
    public static String obtenerCedulaAsignada(String placa) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/com/poo/proyecto/resources/asignaciones.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length > 1 && partes[0].equalsIgnoreCase(placa)) {
                    return partes[1]; // Retorna la cédula asignada
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de asignaciones: " + e.getMessage());
        }

        return null; // No se encontró la placa
    }

}
