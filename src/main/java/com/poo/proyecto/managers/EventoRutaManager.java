package com.poo.proyecto.managers;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventoRutaManager {
    
    public static void registrarEvento(String codigoRuta, String tipoEvento, String descripcion) {
        String archivo = "src/main/java/com/poo/proyecto/resources/eventosRuta.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHora = LocalDateTime.now().format(formatter);

        String linea = String.format("%s | %s | %s | %s\n", codigoRuta, fechaHora, tipoEvento, descripcion);

        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(linea);
            System.out.println("Evento registrado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar el evento: " + e.getMessage());
        }
    }
}
