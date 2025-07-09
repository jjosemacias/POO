package com.poo.proyecto.managers;

import com.poo.proyecto.utils.ValidadorUtils;
import java.io.*;
import java.util.List;

public class EventoRutaManager {

    private static final String ARCHIVO_EVENTOS = "src/main/java/com/poo/proyecto/resources/eventosRuta.txt";

    public static void registrarEvento(String codigoRuta, String tipoEvento) {
        // Registrar el evento en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_EVENTOS, true))) {
            String evento = generarEvento(codigoRuta, tipoEvento);
            writer.write(evento);
            writer.newLine();
            System.out.println("Evento registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar el evento: " + e.getMessage());
        }
        ValidadorUtils.esperar(2000);
    }

    private static String generarEvento(String codigoRuta, String tipoEvento) {
        // Generar la cadena que representa el evento para guardarlo en el archivo como toFile
        return codigoRuta + "|" + tipoEvento + "|" + System.currentTimeMillis(); // Usamos el timestamp como ejemplo para la fecha/hora del evento
    }

    public static List<String> obtenerEventosPorRuta(String codigoRuta) {
        // Método para obtener todos los eventos asociados a una ruta
        // Este código podría ser útil para la consulta de eventos por ruta
        return null;  // Este método se puede implementar para devolver los eventos registrados por ruta
    }
}
