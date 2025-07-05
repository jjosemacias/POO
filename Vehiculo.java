import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

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

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCapacidadCarga() {
        return capacidad_carga;
    }
    public void setCapacidadCarga(String capacidad_carga) {
        this.capacidad_carga = capacidad_carga;
    }
    public boolean isEstadoOperativo() {
        return estado_operativo;
    }
    public void setEstadoOperativo(boolean estado_operativo) {
        this.estado_operativo = estado_operativo;
    }
}