package com.poo.proyecto.managers;

import com.poo.proyecto.models.Paquete;
import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PaqueteManager {

    private static final String ARCHIVO_PAQUETES = "src/main/java/com/poo/proyecto/resources/paquetes.txt";
    private static Set<String> paquetesRegistrados = new HashSet<>();

    public static void registrarPaquete(Paquete paquete) {
        if (paquetesRegistrados.contains(paquete.getCodigo())) {
            System.out.println("El paquete con código " + paquete.getCodigo() + " ya está registrado.");
            ValidadorUtils.esperar(2000);
            return;
        }

        if (!ValidadorUtils.esTelefonoValido(paquete.getTelefonoContacto())) {
            System.out.println("El teléfono de contacto no es válido.");
            ValidadorUtils.esperar(2000);
            return;
        }

        // Guardar el paquete en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PAQUETES, true))) {
            writer.write(paquete.toFileString());
            writer.newLine();
            paquetesRegistrados.add(paquete.getCodigo());
            System.out.println("Paquete registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar el paquete: " + e.getMessage());
        }
        ValidadorUtils.esperar(2000);
    }

    public static boolean existePaquete(String codigo) {
        // Verificar si un paquete existe en el conjunto de paquetes registrados
        return paquetesRegistrados.contains(codigo);
    }
}
