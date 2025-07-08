package com.poo.proyecto.models;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.*;

public class Paquete {
    private String id;
    private String descripcion;
    private int peso;
    private String destinatario;
    private String direccion;
    private String telefono_contacto; // Cambiado a String

    public Paquete(String id, String descripcion, int peso, String destinatario, String direccion, String telefono_contacto) {
        this.id = id;
        this.descripcion = descripcion;
        this.peso = peso;
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.telefono_contacto = telefono_contacto;
    }

    public void almacenarPaquete() {
        String ruta = "src/main/java/com/poo/proyecto/resources/paquetes.txt";
        File archivo = new File(ruta);
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(this.id + "," + this.descripcion + "," + this.peso + ","
                    + this.destinatario + "," + this.direccion + "," + this.telefono_contacto + "\n");
        } catch (IOException e) {
            System.out.println("Error al almacenar el paquete: " + e.getMessage());
        }
    }
    
    public static void crearRuta(String fecha, String placa, String cedula, List<String> codigos){
        String ruta = fecha + "," + placa + "," + cedula + "," + String.join("|", codigos);
        try (FileWriter fw = new FileWriter("rutas.txt", true)){
            BufferedWriter bw = new BufferedWriter(fw); 
            bw.write(ruta);
            bw.newLine();
        }catch (IOException e) {
            System.out.println("Error al guardar la ruta: " + e.getMessage());
        }  
    }

    // Getters
    public String getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getPeso() {
        return peso;
    }
    public String getDestinatario() {
        return destinatario;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getTelefonoContacto() {
        return telefono_contacto;
    }

    // Setters con validación
    public boolean setId(String id) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id;
            return true;
        }
        System.out.println("El ID no puede estar vacío.");
        return false;
    }
    public boolean setDescripcion(String descripcion) {
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            this.descripcion = descripcion;
            return true;
        }
        System.out.println("La descripción no puede estar vacía.");
        return false;
    }
    public boolean setPeso(int peso) {
        if (peso > 0) {
            this.peso = peso;
            return true;
        }
        System.out.println("El peso debe ser mayor a 0.");
        return false;
    }
    public boolean setDestinatario(String destinatario) {
        if (destinatario != null && !destinatario.trim().isEmpty()) {
            this.destinatario = destinatario;
            return true;
        }
        System.out.println("El destinatario no puede estar vacío.");
        return false;
    }
    public boolean setDireccion(String direccion) {
        if (direccion != null && !direccion.trim().isEmpty()) {
            this.direccion = direccion;
            return true;
        }
        System.out.println("La dirección no puede estar vacía.");
        return false;
    }
    public boolean setTelefonoContacto(String telefono_contacto) {
        String regex = "^[0-9]{10}$"; // Regex para validar teléfono ecuatoriano
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefono_contacto);
        if (matcher.matches()) {
            this.telefono_contacto = telefono_contacto;
            return true;
        }
        System.out.println("El teléfono de contacto debe tener 10 dígitos numéricos.");
        return false;
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", peso=" + peso +
                ", destinatario='" + destinatario + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono_contacto=" + telefono_contacto +
                '}';
    }

    public static boolean buscarPaqueteRegistrado(String codigo) {
        String ruta = "src/main/java/com/poo/proyecto/resources/paquetes.txt";
        File archivo = new File(ruta);
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");
                if (datos.length > 0 && datos[0].equals(codigo)) {
                    return true; // Paquete encontrado
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de paquetes: " + e.getMessage());
        }
        return false; // Paquete no encontrado
    }
}
