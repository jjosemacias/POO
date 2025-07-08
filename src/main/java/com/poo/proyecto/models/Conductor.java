package com.poo.proyecto.models;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;


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

    public void almacenarConductor() {
        File archivo = new File("conductores.txt");
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(nombre + "," + cedula + "," + licencia + "," + telefono + "," + correo + "\n");
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


    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getLicencia() {
        return licencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean setCedula(String cedula) {
        String regex = "^[0-9]{10}$"; // Regex para validar cédula ecuatoriana
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cedula);
        if (matcher.matches()) {
            this.cedula = cedula;
            return true;
        }
        return false;
    }

    public boolean setLicencia(String licencia) {
        String regex = "^[A-Fa-f]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(licencia);
        if (matcher.matches()) {
            this.licencia = licencia;
            return true;
        } else {
            System.out.println("Licencia inválida. Debe ser una letra entre A y F.");
            return false;
        }
    }

    public boolean setTelefono(String telefono) {
        String regex = "^[0-9]{10}$"; // Regex para validar teléfono ecuatoriano
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefono);
        if (matcher.matches()) {
            this.telefono = telefono;
            return true;
        }
        System.out.println("Teléfono inválido. Debe tener 10 dígitos.");
        return false;
    }

    public boolean setCorreo(String correo) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,}$"; // Regex para validar correo electrónico
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(correo);
        if (matcher.matches()) {
            this.correo = correo;
            return true;
        }
        System.out.println("Correo inválido.");
        return false;
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", licencia='" + licencia + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }

}
