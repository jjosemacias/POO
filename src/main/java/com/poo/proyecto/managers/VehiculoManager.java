package com.poo.proyecto.managers;

import com.poo.proyecto.models.Vehiculo;
import java.io.FileWriter;
import java.io.IOException;

public class VehiculoManager {

    public static void registrarVehiculo(Vehiculo vehiculo) {
        String archivo = "src/main/java/com/poo/proyecto/resources/vehiculos.txt";
        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(vehiculo.toFileString() + "\n");
            System.out.println("Vehículo registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar el vehículo: " + e.getMessage());
        }
    }
}
