import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Conductor {
    private String nombre;
    private String cedula;
    private String licencia;
    private String telefono;
    private String correo;

    public Conductor(String nombre, String cedula, String licencia, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.licencia = licencia;
        this.telefono = telefono;
    }

    public void almacenarConductor(Conductor conductor) {
        try {
            FileWriter writer = new FileWriter("conductores.txt", true);
            writer.write(conductor.getNombre() + "," + conductor.getCedula() + "," + conductor.getLicencia() + "," + conductor.getTelefono() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al almacenar conductor: " + e.getMessage());
        }
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getLicencia() {
        return licencia;
    }
    
    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
