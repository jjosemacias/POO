package com.poo.proyecto.models;

public class Rastreo {
    private String codigoRuta;
    private String horaSalida;

    public Rastreo(String codigoRuta, String horaSalida) {
        setCodigoRuta(codigoRuta);
        setHoraSalida(horaSalida);
    }

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        if (codigoRuta == null || codigoRuta.isBlank()) {
            throw new IllegalArgumentException("El código de ruta no puede estar vacío.");
        }
        this.codigoRuta = codigoRuta;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        if (horaSalida == null || horaSalida.isBlank()) {
            throw new IllegalArgumentException("La hora de salida no puede estar vacía.");
        }
        this.horaSalida = horaSalida;
    }

    public String toFileString() {
        return codigoRuta + "|" + horaSalida;
    }

    @Override
    public String toString() {
        return "Ruta: " + codigoRuta + ", Salida: " + horaSalida;
    }
}