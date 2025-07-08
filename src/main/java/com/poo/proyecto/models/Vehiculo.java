package com.poo.proyecto.models;

public class Vehiculo {
    private String placa;
    private String modelo;
    private String tipo;
    private double capacidadCarga;
    private String estadoOperativo; // Operativo / Inoperativo

    public Vehiculo(String placa, String modelo, String tipo, double capacidadCarga, String estadoOperativo) {
        setPlaca(placa);
        setModelo(modelo);
        setTipo(tipo);
        setCapacidadCarga(capacidadCarga);
        setEstadoOperativo(estadoOperativo);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("La placa no puede estar vacía.");
        }
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío.");
        }
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo no puede estar vacío.");
        }
        this.tipo = tipo;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        if (capacidadCarga <= 0) {
            throw new IllegalArgumentException("La capacidad de carga debe ser mayor a cero.");
        }
        this.capacidadCarga = capacidadCarga;
    }

    public String getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(String estadoOperativo) {
        if (!estadoOperativo.equalsIgnoreCase("Operativo") && !estadoOperativo.equalsIgnoreCase("Inoperativo")) {
            throw new IllegalArgumentException("El estado operativo debe ser 'Operativo' o 'Inoperativo'.");
        }
        this.estadoOperativo = estadoOperativo;
    }

    public String toFileString() {
        return placa + "|" + modelo + "|" + tipo + "|" + capacidadCarga + "|" + estadoOperativo;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + ", Modelo: " + modelo + ", Tipo: " + tipo +
               ", Capacidad: " + capacidadCarga + "kg, Estado: " + estadoOperativo;
    }
}
