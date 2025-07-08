package com.poo.proyecto.models;

public class Asignacion {
    private final String placaVehiculo;
    private final String cedulaConductor;

    public Asignacion(String placaVehiculo, String cedulaConductor) {
        if (placaVehiculo == null || placaVehiculo.isBlank()) throw new IllegalArgumentException("La placa del vehículo no puede estar vacía.");
        if (cedulaConductor == null || cedulaConductor.isBlank()) throw new IllegalArgumentException("La cédula del conductor no puede estar vacía.");

        this.placaVehiculo = placaVehiculo;
        this.cedulaConductor = cedulaConductor;
    }

    public String getPlacaVehiculo() { return placaVehiculo; }
    public String getCedulaConductor() { return cedulaConductor; }

    public String toFileString() {
        return placaVehiculo + "|" + cedulaConductor;
    }
}
