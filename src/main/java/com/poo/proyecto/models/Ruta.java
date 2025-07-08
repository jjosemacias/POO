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

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        if (codigoRuta == null || codigoRuta.isBlank()) {
            throw new IllegalArgumentException("El código de ruta no puede estar vacío.");
        }
        this.codigoRuta = codigoRuta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.isBlank()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        this.fecha = fecha;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        if (placaVehiculo == null || placaVehiculo.isBlank()) {
            throw new IllegalArgumentException("La placa del vehículo no puede estar vacía.");
        }
        this.placaVehiculo = placaVehiculo;
    }

    public String getCedulaConductor() {
        return cedulaConductor;
    }

    public void setCedulaConductor(String cedulaConductor) {
        if (cedulaConductor == null || cedulaConductor.isBlank()) {
            throw new IllegalArgumentException("La cédula del conductor no puede estar vacía.");
        }
        this.cedulaConductor = cedulaConductor;
    }

    public List<String> getCodigosPaquetes() {
        return codigosPaquetes;
    }

    public void setCodigosPaquetes(List<String> codigosPaquetes) {
        if (codigosPaquetes == null || codigosPaquetes.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un paquete en la ruta.");
        }
        this.codigosPaquetes = codigosPaquetes;
    }

    public String toFileString() {
        String paquetes = String.join(",", codigosPaquetes);
        return codigoRuta + "|" + fecha + "|" + placaVehiculo + "|" + cedulaConductor + "|" + paquetes;
    }

    @Override
    public String toString() {
        return "Ruta: " + codigoRuta + ", Fecha: " + fecha + ", Vehículo: " + placaVehiculo +
               ", Conductor: " + cedulaConductor + ", Paquetes: " + String.join(", ", codigosPaquetes);
    }
}
