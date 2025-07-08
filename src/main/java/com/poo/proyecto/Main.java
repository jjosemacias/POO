package com.poo.proyecto;

import com.poo.proyecto.managers.*;
import com.poo.proyecto.models.*;
import com.poo.proyecto.utils.ValidadorUtils;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;

            do {
                System.out.println("Menú de opciones:");
                System.out.println("1. Registrar Vehículo");
                System.out.println("2. Registrar Conductor");
                System.out.println("3. Asignar Vehículo a Conductor");
                System.out.println("4. Registrar Paquete a Entregar");
                System.out.println("5. Crear Ruta de Entrega");
                System.out.println("6. Iniciar Rastreo de Vehículo");
                System.out.println("7. Registrar Evento de Ruta");
                System.out.println("8. Consultar Historial de Vehículo");
                System.out.println("9. Generar Reporte de Entregas por Ruta");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                if(scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine();  // Consumir la nueva línea
                } else {
                    System.out.println("Por favor ingrese un número válido.");
                    scanner.nextLine();  // Limpiar la entrada incorrecta
                    opcion = -1; // Asignar un valor para que el do-while siga ejecutándose
                }

                switch (opcion) {
                    case 1 -> registrarVehiculo(scanner);
                    case 2 -> registrarConductor(scanner);
                    case 3 -> asignarVehiculoAConductor(scanner);
                    case 4 -> registrarPaquete(scanner);
                    case 5 -> crearRuta(scanner);
                    case 6 -> iniciarRastreo(scanner);
                    case 7 -> registrarEvento(scanner);
                    case 8 -> consultarHistorial(scanner);
                    case 9 -> generarReporte(scanner);
                    case 0 -> System.out.println("Saliendo...");
                    default -> {
                        System.out.println("Opción inválida, por favor intente de nuevo.");
                        ValidadorUtils.esperar(2000);
                    }
                }
            } while (opcion != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void registrarVehiculo(Scanner scanner) {
        String placa = "";
        while (!ValidadorUtils.esPlacaValida(placa)) {
            System.out.print("Ingrese la placa del vehículo (Formato: ABC-1234): ");
            placa = scanner.nextLine();
        }

        System.out.print("Ingrese el modelo del vehículo: ");
        String modelo = scanner.nextLine();
        while (!ValidadorUtils.esCadenaNoVacia(modelo)) {
            System.out.print("Ingrese el modelo del vehículo: ");
            modelo = scanner.nextLine();
        }

        System.out.print("Ingrese el tipo del vehículo: ");
        String tipo = scanner.nextLine();
        while (!ValidadorUtils.esCadenaNoVacia(tipo)) {
            System.out.print("Ingrese el tipo del vehículo: ");
            tipo = scanner.nextLine();
        }

        double capacidadCarga = 0;
        while (capacidadCarga <= 0) {
            System.out.print("Ingrese la capacidad de carga del vehículo [Kg]: ");
            if (scanner.hasNextDouble()) {
                capacidadCarga = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer
            } else {
                scanner.nextLine(); // Limpiar buffer
            }
        }

        String estadoOperativo = "";
        while (!ValidadorUtils.esEstadoOperativoValido(estadoOperativo)) {
            System.out.print("Ingrese el estado operativo (Operativo/Inoperativo): ");
            estadoOperativo = scanner.nextLine();
        }

        Vehiculo vehiculo = new Vehiculo(placa, modelo, tipo, capacidadCarga, estadoOperativo);
        VehiculoManager.registrarVehiculo(vehiculo);
    }

    private static void registrarConductor(Scanner scanner) {
    // Validar nombre (no vacío)
    String nombre = "";
    while (!ValidadorUtils.esCadenaNoVacia(nombre)) {
        System.out.print("Ingrese el nombre del conductor: ");
        nombre = scanner.nextLine();
    }

    // Validar cédula (no vacío y formato válido)
    String cedula = "";
    while (!ValidadorUtils.esCadenaNoVacia(cedula)) {
                System.out.print("No puede ser vacio: ");
                cedula = scanner.nextLine();
        while(!ValidadorUtils.esCedulaValida(cedula)){
            System.out.print("Ingrese la cédula del conductor (10 dígitos): ");
            cedula = scanner.nextLine();
    }
    }
    // Validar licencia (no vacío y formato válido)
    String licencia = "";
    while (!ValidadorUtils.esCadenaNoVacia(licencia) || !ValidadorUtils.esLicenciaValida(licencia)) {
        System.out.print("Ingrese la licencia del conductor (A, A1, B, C1, C, D1, D, E1, E, F, G): ");
        licencia = scanner.nextLine();
    }

    // Validar teléfono (no vacío y formato válido)
    String telefono = "";
    while (!ValidadorUtils.esCadenaNoVacia(telefono) || !ValidadorUtils.esTelefonoValido(telefono)) {
        System.out.print("Ingrese el teléfono del conductor (10 dígitos): ");
        telefono = scanner.nextLine();
    }

    // Validar correo (no vacío y formato válido)
    String correo = "";
    while (!ValidadorUtils.esCadenaNoVacia(correo) || !ValidadorUtils.esCorreoValido(correo)) {
        System.out.print("Ingrese el correo del conductor: ");
        correo = scanner.nextLine();
    }

    // Crear el objeto conductor
    Conductor conductor = new Conductor(nombre, cedula, licencia, telefono, correo);
    ConductorManager.registrarConductor(conductor);
}

    private static void asignarVehiculoAConductor(Scanner scanner) {
        System.out.print("Ingrese la placa del vehículo: ");
        String placaVehiculo = scanner.nextLine();
        while (placaVehiculo.isEmpty()) {
            System.out.print("Ingrese la placa del vehículo: ");
            placaVehiculo = scanner.nextLine();
        }

        System.out.print("Ingrese la cédula del conductor: ");
        String cedulaConductor = scanner.nextLine();
        while (cedulaConductor.isEmpty()) {
            System.out.print("Ingrese la cédula del conductor: ");
            cedulaConductor = scanner.nextLine();
        }

        AsignacionManager.asignarVehiculoAConductor(placaVehiculo, cedulaConductor);
    }

    private static void registrarPaquete(Scanner scanner) {
        System.out.print("Ingrese el código del paquete: ");
        String codigo = scanner.nextLine();
        while (codigo.isEmpty()) {
            System.out.print("Ingrese el código del paquete: ");
            codigo = scanner.nextLine();
        }

        System.out.print("Ingrese la descripción del paquete: ");
        String descripcion = scanner.nextLine();
        while (descripcion.isEmpty()) {
            System.out.print("Ingrese la descripción del paquete: ");
            descripcion = scanner.nextLine();
        }

        double peso = 0;
        while (peso <= 0) {
            System.out.print("Ingrese el peso del paquete: ");
            if (scanner.hasNextDouble()) {
                peso = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
            } else {
                scanner.nextLine(); // Limpiar buffer
            }
        }

        System.out.print("Ingrese el destinatario del paquete: ");
        String destinatario = scanner.nextLine();

        System.out.print("Ingrese la dirección de entrega: ");
        String direccion = scanner.nextLine();

        System.out.print("Ingrese el teléfono de contacto: ");
        String telefonoContacto = scanner.nextLine();

        Paquete paquete = new Paquete(codigo, descripcion, peso, destinatario, direccion, telefonoContacto);
        PaqueteManager.registrarPaquete(paquete);
    }

    private static void crearRuta(Scanner scanner) {
        System.out.print("Ingrese el código de la ruta: ");
        String codigoRuta = scanner.nextLine();
        while (codigoRuta.isEmpty()) {
            System.out.print("Ingrese el código de la ruta: ");
            codigoRuta = scanner.nextLine();
        }

        System.out.print("Ingrese la fecha de la ruta (dd/mm/yyyy): ");
        String fecha = scanner.nextLine();

        System.out.print("Ingrese la placa del vehículo: ");
        String placaVehiculo = scanner.nextLine();

        System.out.print("Ingrese la cédula del conductor: ");
        String cedulaConductor = scanner.nextLine();

        System.out.print("Ingrese los códigos de los paquetes (separados por coma): ");
        String paquetesStr = scanner.nextLine();
        List<String> codigosPaquetes = List.of(paquetesStr.split(","));

        Ruta ruta = new Ruta(codigoRuta, fecha, placaVehiculo, cedulaConductor, codigosPaquetes);
        RutaManager.crearRuta(ruta);
    }

    private static void iniciarRastreo(Scanner scanner) {
        System.out.print("Ingrese el código de la ruta para iniciar el rastreo: ");
        String codigoRuta = scanner.nextLine();
        RastreoManager.iniciarRastreo(codigoRuta);
    }

    private static void registrarEvento(Scanner scanner) {
        System.out.print("Ingrese el código de la ruta: ");
        String codigoRuta = scanner.nextLine();

        System.out.print("Ingrese el tipo de evento (Parada, Entrega, Incidente): ");
        String tipoEvento = scanner.nextLine();

        EventoRutaManager.registrarEvento(codigoRuta, tipoEvento);
    }

    private static void consultarHistorial(Scanner scanner) {
        System.out.print("Ingrese la placa del vehículo: ");
        String placaVehiculo = scanner.nextLine();
        HistorialManager.consultarHistorial(placaVehiculo);
    }

    private static void generarReporte(Scanner scanner) {
        System.out.print("Ingrese el código de la ruta para generar el reporte: ");
        String codigoRuta = scanner.nextLine();
        ReporteRutaManager.generarReporteRuta(codigoRuta);
    }
}
