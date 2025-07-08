package com.poo.proyecto.models;

public class Conductor {
    private final String nombre;
    private final String cedula;
    private final String licencia;
    private final String telefono;
    private final String correo;

    public Conductor(String nombre, String cedula, String licencia, String telefono, String correo) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío.");
        if (cedula == null || cedula.isBlank()) throw new IllegalArgumentException("La cédula no puede estar vacía.");
        if (licencia == null || licencia.isBlank()) throw new IllegalArgumentException("La licencia no puede estar vacía.");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        if (correo == null || correo.isBlank()) throw new IllegalArgumentException("El correo no puede estar vacío.");

        this.nombre = nombre;
        this.cedula = cedula;
        this.licencia = licencia;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getLicencia() { return licencia; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }

    public String toFileString() {
        return nombre + "|" + cedula + "|" + licencia + "|" + telefono + "|" + correo;
    }
}
