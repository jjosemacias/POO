import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Vehiculo{
    private String placa;
    private String modelo;
    private String tipo;
    private String capacidad_carga;
    private boolean estado_operativo;

    public Vehiculo(String placa, String modelo, String tipo, String capacidad_carga, boolean estado_operativo) {
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.capacidad_carga = capacidad_carga;
        this.estado_operativo = estado_operativo;
    }

    public static void almacenarVehiculo(Vehiculo vehiculo) {
        File archivo = new File("vehiculos.txt");
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(vehiculo.getPlaca() + "," + vehiculo.getModelo() + "," + vehiculo.getTipo() + ","
                    + vehiculo.getCapacidadCarga() + "," + (vehiculo.isEstadoOperativo() ? "1" : "0") + "\n");
        } catch (IOException e) {
            System.out.println("Error al almacenar el vehiculo: " + e.getMessage());
        }
    }

    public static Vehiculo buscarVehiculoRegistrado(String placa) {
        File archivo = new File("vehiculos.txt");
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");
                if (datos[0].equals(placa)) {
                    String modelo = datos[1];
                    String tipo = datos[2];
                    String capacidadCarga = datos[3];
                    boolean estadoOperativo = datos[4].equals("1");
                    return new Vehiculo(placa, modelo, tipo, capacidadCarga, estadoOperativo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar el vehiculo: " + e.getMessage());
        }
        return null;
    }

    public void asignarConductor(Conductor conductor) {
        File archivo = new File("asignaciones.txt");
        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(this.placa + "," + conductor.getCedula() + "\n");
        } catch (IOException e) {
            System.out.println("Error al asignar el conductor al vehiculo: " + e.getMessage());
        }
    }
    
    // Getters
    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public String getTipo() {
        return tipo;
    }
    public String getCapacidadCarga() {
        return capacidad_carga;
    }
    public boolean isEstadoOperativo() {
        return estado_operativo;
    }

    // Setters
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setCapacidadCarga(String capacidad_carga) {
        this.capacidad_carga = capacidad_carga;
    }
    public void setEstadoOperativo(boolean estado_operativo) {
        this.estado_operativo = estado_operativo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidad_carga='" + capacidad_carga + '\'' +
                ", estado_operativo=" + estado_operativo +
                '}';
    }
}