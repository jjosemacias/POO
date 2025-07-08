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

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getCapacidadCarga() { return capacidadCarga; }
    public void setCapacidadCarga(double capacidadCarga) { this.capacidadCarga = capacidadCarga; }

    public String getEstadoOperativo() { return estadoOperativo; }
    public void setEstadoOperativo(String estadoOperativo) { this.estadoOperativo = estadoOperativo; }

    public String toFileString() {
        return placa + "|" + modelo + "|" + tipo + "|" + capacidadCarga + "|" + estadoOperativo;
    }
}
