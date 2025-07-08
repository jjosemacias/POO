package com.poo.proyecto.managers;

import com.poo.proyecto.models.Conductor;
import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;

public class ConductorManager {

    public static void registrarConductor(Conductor conductor) {
        if (!ValidadorUtils.esCedulaValida(conductor.getCedula())) {
            System.out.println("La cédula debe tener 10 dígitos.");
            ValidadorUtils.esperar(2000);
            return;
        }
        if (!ValidadorUtils.esCorreoValido(conductor.getCorreo())) {
            System.out.println("El correo no es válido.");
            ValidadorUtils.esperar(2000);
            return;
        }
        String archivo = "src/main/java/com/poo/proyecto/resources/conductores.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(conductor.toFileString());
            writer.newLine();
            System.out.println("Conductor registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar conductor: " + e.getMessage());
        }
        ValidadorUtils.esperar(2000);
    }

    public static boolean existeCedulaRegistrada(String cedula) {
        String archivo = "src/main/java/com/poo/proyecto/resources/conductores.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length > 1 && partes[1].equals(cedula)) {
                    return true; // La cédula ya está registrada
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de conductores: " + e.getMessage());
        }
        System.out.println("La cédula no está registrada.");
        return false; // La cédula no está registrada
    }
}
