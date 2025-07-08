package com.poo.proyecto.models;

public class Rastreo {
    private String codigoRuta;
    private String horaSalida;

    public Rastreo(String codigoRuta, String horaSalida) {
        setCodigoRuta(codigoRuta);
        setHoraSalida(horaSalida);
    }

    public String getCodigoRuta() { return codigoRuta; }
    public void setCodigoRuta(String codigoRuta) { this.codigoRuta = codigoRuta; }

    public String getHoraSalida() { return horaSalida; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }

    public String toFileString() {
        return codigoRuta + "|" + horaSalida;
    }
}
