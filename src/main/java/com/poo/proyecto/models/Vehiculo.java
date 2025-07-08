package com.poo.proyecto.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;
import com.poo.proyecto.models.Conductor;

public class Vehiculo{
    private String placa;
    private String modelo;
    private String tipo;
    private int capacidad_carga;
    private boolean estado_operativo;

    public Vehiculo(String placa, String modelo, String tipo, int capacidad_carga, boolean  estado_operativo) {
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.capacidad_carga = capacidad_carga;
        this.estado_operativo = estado_operativo;
    }

    public void almacenarVehiculo() {
        String ruta = "src/main/java/com/poo/proyecto/resources/vehiculos.txt";
        File archivo = new File(ruta);
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(placa+ "," + modelo + "," + tipo + "," + capacidad_carga + "," + (estado_operativo ? "1" : "0") + "\n");
        } catch (IOException e) {
            System.out.println("Error al almacenar el vehiculo: " + e.getMessage());
        }
    }

    public static Vehiculo buscarVehiculoRegistrado(String placa) {
        String ruta = "src/main/java/com/poo/proyecto/resources/vehiculos.txt";
        File archivo = new File(ruta);
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");
                if (datos[0].equals(placa)) {
                    String modelo = datos[1];
                    String tipo = datos[2];
                    int capacidadCarga = Integer.parseInt(datos[3]);
                    boolean estadoOperativo = datos[4].equals("1");
                    return new Vehiculo(placa, modelo, tipo, capacidadCarga, estadoOperativo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar el vehiculo: " + e.getMessage());
        }
        return null;
    }

    public void asignarConductor(Conductor conductor) {
        String ruta = "src/main/java/com/poo/proyecto/resources/asignaciones.txt";
        File archivo = new File(ruta);
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(this.placa + "," + conductor.getCedula() + "\n");
        } catch (IOException e) {
            System.out.println("Error al asignar el conductor al vehiculo: " + e.getMessage());
        }
    }
    public static boolean verificarConductorAsignado(String placa) {
        String ruta = "src/main/java/com/poo/proyecto/resources/asignaciones.txt";
        File archivo = new File(ruta);
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");
                if (datos[0].equals(placa)) {
                    return true; // El vehículo tiene un conductor asignado
                }
            }
        } catch (IOException e) {
            System.out.println("Error al verificar la asignación del conductor: " + e.getMessage());
        }
        System.out.println("El vehículo con placa " + placa + " no tiene un conductor asignado.");
        return false; // El vehículo no tiene un conductor asignado
    }

    public static String ConductorAsignado(String placa) {
        String ruta = "src/main/java/com/poo/proyecto/resources/asignaciones.txt";
        File archivo = new File(ruta);
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");
                if (datos[0].equals(placa)) {
                    return datos[1]; // Retorna la cédula del conductor asignado
                }
            }
        } catch (IOException e) {
            System.out.println("Error al verificar la asignación del conductor: " + e.getMessage());
        }
        System.out.println("El vehículo con placa " + placa + " no tiene un conductor asignado.");
        return null;
    }
    
    // Getters
    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public String getTipo() {
        return tipo;
    }
    public int  getCapacidadCarga() {
        return capacidad_carga;
    }
    public boolean isEstadoOperativo() {
        return estado_operativo;
    }

    // Setters
    public boolean setPlaca(String placa) {
        String regex = "^[A-Z]{3}-[0-9]{4}$"; // Formato: AAA-0000
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(placa);
        if (matcher.matches()) {
            this.placa = placa;
            return true;
        } else {
            System.out.println("Placa inválida. Debe seguir el formato AAA-0000.");
            return false;
        }
    }
    public boolean setModelo(String modelo) {
        if (modelo != null && !modelo.trim().isEmpty()) {
            this.modelo = modelo;
            return true;
        } else {
            System.out.println("Modelo inválido. No puede estar vacío.");
            return false;
        }
    }
    public boolean setTipo(String tipo) {
        if (tipo != null && !tipo.trim().isEmpty()) {
            this.tipo = tipo;
            return true;
        } else {
            System.out.println("Tipo inválido. No puede estar vacío.");
            return false;
        }
    }
    public boolean setCapacidadCarga(int capacidad_carga) {
        if (capacidad_carga > 0) {
            this.capacidad_carga = capacidad_carga;
            return true;
        } else {
            System.out.println("Capacidad de carga inválida. Debe ser un número positivo.");
            return false;
        }
    }

    public void setEstadoOperativo(boolean estado_operativo) {
        this.estado_operativo = estado_operativo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidad_carga='" + capacidad_carga + '\'' +
                ", estado_operativo=" + estado_operativo +
                '}';
    }
}