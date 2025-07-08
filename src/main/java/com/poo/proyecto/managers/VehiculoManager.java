package com.poo.proyecto.managers;

import com.poo.proyecto.models.Vehiculo;
import java.io.*;

public class VehiculoManager {

    private static final String ARCHIVO_VEHICULOS = "src/main/java/com/poo/proyecto/resources/vehiculos.txt";

    // Método para verificar si la placa ya está registrada
    public static boolean existePlacaRegistrada(String placa) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_VEHICULOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length > 0 && partes[0].equalsIgnoreCase(placa)) {
                    return true; // La placa ya está registrada
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de vehículos: " + e.getMessage());
        }
        System.out.println("La placa no está registrada.");
        return false; // La placa no está registrada
    }

    public static void registrarVehiculo(Vehiculo vehiculo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_VEHICULOS, true))) {
            writer.write(vehiculo.toFileString());
            writer.newLine();
            System.out.println("Vehículo registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar el vehículo: " + e.getMessage());
        }
    }
    
}
