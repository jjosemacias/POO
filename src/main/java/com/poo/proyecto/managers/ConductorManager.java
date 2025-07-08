package com.poo.proyecto.managers;

import com.poo.proyecto.models.Conductor;
import java.io.*;

public class ConductorManager {

    public static void registrarConductor(Conductor conductor) {
        String archivo = "src/main/java/com/poo/proyecto/resources/conductores.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(conductor.toFileString());
            writer.newLine();
            System.out.println("Conductor registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar conductor: " + e.getMessage());
        }
    }

    public static boolean existeConductorPorCedula(String cedula) {
        String archivo = "src/main/java/com/poo/proyecto/resources/conductores.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains(cedula)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar conductor: " + e.getMessage());
        }
        return false;
    }
}