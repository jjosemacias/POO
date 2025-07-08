package src.main.java.com.poo.proyecto.models;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Conductor {
    private String nombre;
    private String cedula;
    private String licencia;
    private String telefono;
    private String correo;

    public Conductor(String nombre, String cedula, String licencia, String telefono, String correo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.licencia = licencia;
        this.telefono = telefono;
        this.correo = correo;
    }

    public void almacenarConductor(Conductor conductor) {
        File archivo = new File("conductores.txt");
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(conductor.getNombre() + "," + conductor.getCedula() + "," + conductor.getLicencia() + ","
                    + conductor.getTelefono() + "," + conductor.getCorreo() + "\n");
        } catch (IOException e) {
            System.out.println("Error al almacenar el conductor: " + e.getMessage());
        }
    }

    public static Conductor conductorLibre() {
    try (BufferedReader br = new BufferedReader(new FileReader("conductores.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length >= 5) {
                String cedula = datos[1];
                boolean asignado = false;

                try (BufferedReader br2 = new BufferedReader(new FileReader("asignaciones.txt"))) {
                    String asignacion;
                    while ((asignacion = br2.readLine()) != null) {
                        String[] datosAsignacion = asignacion.split(",");
                        if (datosAsignacion.length >= 2 && datosAsignacion[1].equals(cedula)) {
                            asignado = true;
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo de asignaciones: " + e.getMessage());
                }

                if (!asignado) {
                    // Devuelve el primer conductor libre encontrado
                    return new Conductor(datos[0], datos[1], datos[2], datos[3], datos[4]);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo de conductores: " + e.getMessage());
    }
    return null; // No se encontró ningún conductor libre
}


    //getter y setters
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
