package com.poo.proyecto.models;

public class Paquete {
    private String codigo;
    private String descripcion;
    private double peso;
    private String destinatario;
    private String direccion;
    private String telefonoContacto;

    public Paquete(String codigo, String descripcion, double peso, String destinatario, String direccion, String telefonoContacto) {
        setCodigo(codigo);
        setDescripcion(descripcion);
        setPeso(peso);
        setDestinatario(destinatario);
        setDireccion(direccion);
        setTelefonoContacto(telefonoContacto);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        this.descripcion = descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a cero.");
        }
        this.peso = peso;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        if (destinatario == null || destinatario.isBlank()) {
            throw new IllegalArgumentException("El destinatario no puede estar vacío.");
        }
        this.destinatario = destinatario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.direccion = direccion;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        if (telefonoContacto == null || telefonoContacto.isBlank()) {
            throw new IllegalArgumentException("El teléfono de contacto no puede estar vacío.");
        }
        this.telefonoContacto = telefonoContacto;
    }

    public String toFileString() {
        return codigo + "|" + descripcion + "|" + peso + "|" + destinatario + "|" + direccion + "|" + telefonoContacto;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Descripción: " + descripcion + ", Peso: " + peso +
               "kg, Destinatario: " + destinatario + ", Dirección: " + direccion +
               ", Teléfono: " + telefonoContacto;
    }
}

