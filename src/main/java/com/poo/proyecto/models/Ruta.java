package com.poo.proyecto.models;
import java.util.ArrayList;
import java.util.regex.*;
import com.poo.proyecto.models.Vehiculo;
import java.io.FileWriter;
import java.io.IOException;

public class Ruta {
    private String fecha;
    private String placaVehiculo;
    private String cedulaConductor;
    private ArrayList<String> codigosPaquetes;

    public Ruta(String fecha, String placaVehiculo, String cedulaConductor, ArrayList<String> codigosPaquetes) {
        this.fecha = fecha;
        this.placaVehiculo = placaVehiculo;
        this.cedulaConductor = cedulaConductor;
        this.codigosPaquetes = codigosPaquetes;
    }

    public void almacenarRuta(){
        String ruta = "src/main/java/com/poo/proyecto/resources/rutas.txt";
        try (FileWriter writer = new FileWriter(ruta, true)) {
            writer.write(this.fecha + "," + this.placaVehiculo + "," + this.cedulaConductor + "," + String.join("|", this.codigosPaquetes) + "\n");
        } catch (IOException e) {
            System.out.println("Error al almacenar la ruta: " + e.getMessage());
        }
    }

    public String getFecha() {
        return fecha;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public String getCedulaConductor() {
        return cedulaConductor;
    }

    public ArrayList<String> getCodigosPaquetes() {
        return codigosPaquetes;
    }

    public boolean setFecha(String fecha) {
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        if (fecha != null && fecha.matches(regex)) {
            this.fecha = fecha;
            return true;
        } else {
            System.out.println("Fecha inválida. Debe ser en formato dd/MM/yyyy.");
            return false;
        }
    }
    public boolean setPlacaVehiculo(String placaVehiculo) {
        if (Vehiculo.buscarVehiculoRegistrado(placaVehiculo) instanceof Vehiculo && Vehiculo.verificarConductorAsignado(placaVehiculo)) {
            this.placaVehiculo = placaVehiculo;
            this.cedulaConductor = Vehiculo.ConductorAsignado(placaVehiculo);
            System.out.println("Placa de vehículo registrada y tiene conductor asignado: " + this.cedulaConductor);
            return true;
        } else {
            System.out.println("Placa de vehículo no registrada o no tiene conductor asignado.");
            return false;
        }
    }
    public void setCodigosPaquetes(ArrayList<String> codigosPaquetes) {
        if (codigosPaquetes != null && !codigosPaquetes.isEmpty()) {
            this.codigosPaquetes = codigosPaquetes;
        } else {
            System.out.println("La lista de códigos de paquetes no puede estar vacía.");
        }
    }
}
