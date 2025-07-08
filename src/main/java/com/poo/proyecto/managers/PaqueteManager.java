package com.poo.proyecto.managers;

import com.poo.proyecto.models.Paquete;
import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;

public class PaqueteManager {

    private static final String ARCHIVO_PAQUETES = "src/main/java/com/poo/proyecto/resources/paquetes.txt";

    public static void registrarPaquete(Paquete paquete) {
        // Guardar el paquete en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PAQUETES, true))) {
            writer.write(paquete.toFileString());
            writer.newLine();
            System.out.println("Paquete registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar el paquete: " + e.getMessage());
        }
        ValidadorUtils.esperar(2000);
    }

    public static boolean existePaquete(String codigo){
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_PAQUETES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length > 0 && partes[0].equalsIgnoreCase(codigo)) {
                    System.out.println("El paquete con código " + codigo + " ya está registrado.");
                    return true; // El paquete ya está registrado
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de paquetes: " + e.getMessage());
        }
        return false; // El paquete no está registrado

    }
}
