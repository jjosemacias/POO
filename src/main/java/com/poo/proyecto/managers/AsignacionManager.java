package com.poo.proyecto.managers;

import com.poo.proyecto.models.Asignacion;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AsignacionManager {

    public static void asignarVehiculo(String placa, String cedulaConductor) {
        String archivo = "src/main/java/com/poo/proyecto/resources/asignaciones.txt";

        if (vehiculoYaAsignado(placa)) {
            System.out.println("Este vehículo ya ha sido asignado a un conductor.");
            return;
        }

        if (!vehiculoEstaOperativo(placa)) {
            System.out.println("El vehículo no está operativo.");
            return;
        }

        Asignacion asignacion = new Asignacion(placa, cedulaConductor);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(asignacion.toFileString());
            writer.newLine();
            System.out.println("Asignación registrada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar asignación: " + e.getMessage());
        }
    }

    public static boolean vehiculoYaAsignado(String placa) {
        String archivo = "src/main/java/com/poo/proyecto/resources/asignaciones.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(placa + "|")) {
                    return true;
                }
            }
        } catch (IOException e) {
            // Silencio por archivo inexistente (primera vez)
        }
        return false;
    }

    public static boolean vehiculoEstaOperativo(String placa) {
        String archivo = "src/main/java/com/poo/proyecto/resources/vehiculos.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(placa + "|")) {
                    return linea.toLowerCase().contains("operativo");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al verificar vehículo: " + e.getMessage());
        }
        return false;
    }

    public static String obtenerConductorAsignado(String placaVehiculo) {
    List<Asignacion> asignaciones = listarAsignaciones();
    for (Asignacion asignacion : asignaciones) {
        if (asignacion.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo)) {
            return asignacion.getCedulaConductor();
        }
    }
    return null;
}
    public static List<Asignacion> listarAsignaciones() {
        String archivo = "src/main/java/com/poo/proyecto/resources/asignaciones.txt";
        List<Asignacion> asignaciones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 2) {
                    asignaciones.add(new Asignacion(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar asignaciones: " + e.getMessage());
        }
        return asignaciones;
    }

}