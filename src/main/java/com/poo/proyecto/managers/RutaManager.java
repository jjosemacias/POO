package com.poo.proyecto.managers;

import com.poo.proyecto.models.Ruta;
import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RutaManager {

    private static final String ARCHIVO = "src/main/java/com/poo/proyecto/resources/rutas.txt";

    public static void crearRuta(Ruta ruta) {
        if (!ValidadorUtils.esPlacaValida(ruta.getPlacaVehiculo())) {
            System.out.println("La placa del vehículo no es válida.");
            ValidadorUtils.esperar(2000);
            return;
        }

        // Verificar que la ruta no exista
        if (existeRuta(ruta.getCodigoRuta())) {
            System.out.println("Ya existe una ruta con ese código.");
            ValidadorUtils.esperar(2000);
            return;
        }

        // Verificar existencia de los paquetes
        if (!verificarPaquetesExistentes(ruta.getCodigosPaquetes())) {
            System.out.println("Uno o más paquetes no existen.");
            ValidadorUtils.esperar(2000);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(ruta.toFileString());
            writer.newLine();
            System.out.println("Ruta creada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar ruta: " + e.getMessage());
        }
        ValidadorUtils.esperar(2000);
    }

    public static boolean existeRuta(String codigoRuta) {
        return listarRutas().stream()
                .anyMatch(r -> r.getCodigoRuta().equalsIgnoreCase(codigoRuta));
    }

    public static List<Ruta> listarRutas() {
        List<Ruta> rutas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 5) {
                    String codigo = partes[0];
                    String fecha = partes[1];
                    String placa = partes[2];
                    String cedula = partes[3];
                    List<String> paquetes = List.of(partes[4].split(","));
                    rutas.add(new Ruta(codigo, fecha, placa, cedula, paquetes));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer rutas: " + e.getMessage());
        }
        return rutas;
    }

    public static boolean verificarPaquetesExistentes(List<String> codigos) {
        // Verificar si todos los paquetes existen en el sistema
        for (String codigo : codigos) {
            if (!PaqueteManager.existePaquete(codigo)) {
                return false;
            }
        }
        return true;
    }

    public static void consultarHistorialRuta(String codigoRuta) {
        List<Ruta> rutas = listarRutas();
        Ruta ruta = rutas.stream()
                .filter(r -> r.getCodigoRuta().equalsIgnoreCase(codigoRuta))
                .findFirst()
                .orElse(null);

        if (ruta != null) {
            System.out.println("Historial de la ruta: " + ruta.getCodigoRuta());
            System.out.println("Fecha: " + ruta.getFecha());
            System.out.println("Placa del vehículo: " + ruta.getPlacaVehiculo());
            System.out.println("Cédula del conductor: " + ruta.getCedulaConductor());
            System.out.println("Paquetes asignados: " + String.join(", ", ruta.getCodigosPaquetes()));
        } else {
            System.out.println("Ruta no encontrada.");
        }
        ValidadorUtils.esperar(2000);
    }
}
