package com.poo.proyecto.managers;

import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;

public class AsignacionManager {

    public static void asignarVehiculoAConductor(String placaVehiculo, String cedulaConductor) {
        String archivoVehiculos = "src/main/java/com/poo/proyecto/resources/vehiculos.txt";
        String archivoConductores = "src/main/java/com/poo/proyecto/resources/conductores.txt";
        String archivoAsignaciones = "src/main/java/com/poo/proyecto/resources/asignaciones.txt";

        // Verificar si el vehículo y conductor existen y si el vehículo está operativo
        if (!existeVehiculo(placaVehiculo) || !existeConductor(cedulaConductor) || !vehiculoDisponible(placaVehiculo)) {
            System.out.println("No se puede asignar el vehículo al conductor. Verifique los datos.");
            ValidadorUtils.esperar(2000);
            return;
        }

        // Guardar la asignación
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoAsignaciones, true))) {
            writer.write(placaVehiculo + "|" + cedulaConductor + "\\n");
            System.out.println("Vehículo asignado al conductor exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar la asignación: " + e.getMessage());
        }
        ValidadorUtils.esperar(2000);
    }

    private static boolean existeVehiculo(String placa) {
        // Lógica para verificar si el vehículo existe en 'vehiculos.txt'
        return true;
    }

    private static boolean existeConductor(String cedula) {
        // Lógica para verificar si el conductor existe en 'conductores.txt'
        return true;
    }

    private static boolean vehiculoDisponible(String placa) {
        // Lógica para verificar si el vehículo está operativo
        return true;
    }
}
