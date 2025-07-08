package com.poo.proyecto.managers;

import com.poo.proyecto.models.Rastreo;
import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RastreoManager {

    public static void iniciarRastreo(String codigoRuta) {
        String archivo = "src/main/java/com/poo/proyecto/resources/rastreo.txt";

        if (rastreoYaIniciado(codigoRuta)) {
            System.out.println("Ya se ha iniciado el rastreo para esta ruta.");
            ValidadorUtils.esperar(2000);
            return;
        }

        String horaSalida = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Rastreo rastreo = new Rastreo(codigoRuta, horaSalida);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(rastreo.toFileString());
            writer.newLine();
            System.out.println("Rastreo iniciado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al iniciar rastreo: " + e.getMessage());
        }
        ValidadorUtils.esperar(2000);
    }

    public static boolean rastreoYaIniciado(String codigoRuta) {
        String archivo = "src/main/java/com/poo/proyecto/resources/rastreo.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith(codigoRuta + "|")) {
                    return true;
                }
            }
        } catch (IOException e) {
            // archivo no existe aún, está bien
        }
        return false;
    }
}
