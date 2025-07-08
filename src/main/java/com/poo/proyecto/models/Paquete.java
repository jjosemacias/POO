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

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefonoContacto() { return telefonoContacto; }
    public void setTelefonoContacto(String telefonoContacto) { this.telefonoContacto = telefonoContacto; }

    public String toFileString() {
        return codigo + "|" + descripcion + "|" + peso + "|" + destinatario + "|" + direccion + "|" + telefonoContacto;
    }
}
