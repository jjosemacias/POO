package com.poo.proyecto.managers;

import com.poo.proyecto.models.Paquete;
import java.io.*;

public class PaqueteManager {

    public static void registrarPaquete(Paquete paquete) {
        String archivo = "src/main/java/com/poo/proyecto/resources/paquetes.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(paquete.toFileString());
            writer.newLine();
            System.out.println("Paquete registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar paquete: " + e.getMessage());
        }
    }

    public static boolean existePaquete(String codigo) {
        String archivo = "src/main/java/com/poo/proyecto/resources/paquetes.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(codigo + "|")) {
                    return true;
                }
            }
        } catch (IOException e) {
            // archivo no existe aún, está bien
        }
        return false;
    }
}