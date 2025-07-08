package com.poo.proyecto.models;

import java.util.List;

public class Ruta {
    private String codigoRuta; // único
    private String fecha;
    private String placaVehiculo;
    private String cedulaConductor;
    private List<String> codigosPaquetes;

    public Ruta(String codigoRuta, String fecha, String placaVehiculo, String cedulaConductor, List<String> codigosPaquetes) {
        setCodigoRuta(codigoRuta);
        setFecha(fecha);
        setPlacaVehiculo(placaVehiculo);
        setCedulaConductor(cedulaConductor);
        setCodigosPaquetes(codigosPaquetes);
    }

    public String getCodigoRuta() { return codigoRuta; }
    public void setCodigoRuta(String codigoRuta) { this.codigoRuta = codigoRuta; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getPlacaVehiculo() { return placaVehiculo; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }

    public String getCedulaConductor() { return cedulaConductor; }
    public void setCedulaConductor(String cedulaConductor) { this.cedulaConductor = cedulaConductor; }

    public List<String> getCodigosPaquetes() { return codigosPaquetes; }
    public void setCodigosPaquetes(List<String> codigosPaquetes) { this.codigosPaquetes = codigosPaquetes; }

    public String toFileString() {
        return codigoRuta + "|" + fecha + "|" + placaVehiculo + "|" + cedulaConductor + "|" + String.join(",", codigosPaquetes);
    }
}
