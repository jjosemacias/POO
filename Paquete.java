public class Paquete {
    private String id;
    private String descripcion;
    private String peso;
    private String destinatario;
    private String direccion;
    private int telefono_contacto;

    public Paquete(String id, String descripcion, String peso, String destinatario, String direccion, int telefono_contacto) {
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.telefono_contacto = telefono_contacto;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getPeso() {
        return peso;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }
    public String getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public int getTelefonoContacto() {
        return telefono_contacto;
    }
    public void setTelefonoContacto(int telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }
}
