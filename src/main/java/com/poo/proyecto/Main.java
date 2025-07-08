package com.poo.proyecto;

import com.poo.proyecto.managers.*;
import com.poo.proyecto.models.*;
import com.poo.proyecto.utils.ValidadorUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int opcion = -1;

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                mostrarMenu();
                System.out.print("Seleccione una opción: ");

                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Opción inválida\n");
                    esperar(1500);
                    continue;
                }

                switch (opcion) {
                    case 1 -> {
                        System.out.println("== Registrar Vehículo ==");
                        try {
                            String placa;
                            do {
                                placa = leerCampoObligatorio(scanner, "Placa (AAA-0000): ");
                                if (!ValidadorUtils.esPlacaValida(placa)) {
                                    System.out.println("Placa inválida. Formato esperado: AAA-0000");
                                    esperar(1000);
                                    placa = null;
                                }
                            } while (placa == null);

                            String modelo = leerCampoObligatorio(scanner, "Modelo: ");
                            String tipo = leerCampoObligatorio(scanner, "Tipo: ");

                            double capacidad = 0;
                            while (true) {
                                try {
                                    System.out.print("Capacidad de carga (kg): ");
                                    capacidad = Double.parseDouble(scanner.nextLine());
                                    if (capacidad <= 0) {
                                        System.out.println("Debe ser un valor positivo.");
                                        esperar(1000);
                                    } else break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Debe ingresar un número válido.");
                                    esperar(1000);
                                }
                            }

                            String estado;
                            do {
                                estado = leerCampoObligatorio(scanner, "Estado operativo (Operativo/Inoperativo): ");
                                if (!estado.equalsIgnoreCase("Operativo") && !estado.equalsIgnoreCase("Inoperativo")) {
                                    System.out.println("Estado inválido. Debe ser 'Operativo' o 'Inoperativo'.");
                                    esperar(1000);
                                    estado = null;
                                }
                            } while (estado == null);

                            Vehiculo v = new Vehiculo(placa, modelo, tipo, capacidad, estado);
                            VehiculoManager.registrarVehiculo(v);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        esperar(1500);
                    }

                    case 2 -> {
                        System.out.println("== Registrar Conductor ==");
                        try {
                            String nombre = leerCampoObligatorio(scanner, "Nombre: ");

                            String cedula;
                            do {
                                cedula = leerCampoObligatorio(scanner, "Cédula (10 dígitos): ");
                                if (!ValidadorUtils.esCedulaValida(cedula)) {
                                    System.out.println("Cédula inválida. Debe contener exactamente 10 dígitos");
                                    esperar(1000);
                                    cedula = null;
                                } else if (ConductorManager.existeConductorPorCedula(cedula)) {
                                    System.out.println("Ya existe un conductor con esa cédula");
                                    esperar(1000);
                                    cedula = null;
                                }
                            } while (cedula == null);

                            String licencia;
                            do {
                                licencia = leerCampoObligatorio(scanner, "Licencia (A, A1, B, C1, C, D1, D, E1, E, F, G): ");
                                if (!ValidadorUtils.esLicenciaValida(licencia)) {
                                    System.out.println("Licencia inválida. Clasificaciones válidas: A, A1, B, C1, C, D1, D, E1, E, F, G");
                                    esperar(1000);
                                    licencia = null;
                                }
                            } while (licencia == null);

                            String telefono;
                            do {
                                telefono = leerCampoObligatorio(scanner, "Teléfono (10 dígitos): ");
                                if (!ValidadorUtils.esTelefonoValido(telefono)) {
                                    System.out.println("Teléfono inválido. Debe contener exactamente 10 dígitos");
                                    esperar(1000);
                                    telefono = null;
                                }
                            } while (telefono == null);

                            String correo;
                            do {
                                correo = leerCampoObligatorio(scanner, "Correo: ");
                                if (!ValidadorUtils.esCorreoValido(correo)) {
                                    System.out.println("Correo inválido. Ejemplo: nombre@dominio.com");
                                    esperar(1000);
                                    correo = null;
                                }
                            } while (correo == null);

                            Conductor conductor = new Conductor(nombre, cedula, licencia, telefono, correo);
                            ConductorManager.registrarConductor(conductor);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        esperar(1500);
                    }
                    case 3 -> {
                        System.out.println("== Asignar Vehículo a Conductor ==");
                        String placa = leerCampoObligatorio(scanner, "Placa del vehículo: ");
                        String cedula = leerCampoObligatorio(scanner, "Cédula del conductor: ");
                        if (!ConductorManager.existeConductorPorCedula(cedula)) {
                            System.out.println("Conductor no encontrado.");
                        } else {
                            AsignacionManager.asignarVehiculo(placa, cedula);
                        }
                        esperar(1500);
                    }
                    case 4 -> {
                        System.out.println("== Registrar Paquete ==");
                        try {
                            String codigo = leerCampoObligatorio(scanner, "Código: ");
                            if (PaqueteManager.existePaquete(codigo)) {
                                System.out.println("Ya existe un paquete con ese código");
                                esperar(1500);
                                break;
                            }
                            String descripcion = leerCampoObligatorio(scanner, "Descripción: ");
                            double peso;
                            while (true) {
                                try {
                                    System.out.print("Peso (kg): ");
                                    peso = Double.parseDouble(scanner.nextLine());
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Ingrese un número válido.");
                                    esperar(1000);
                                }
                            }
                            String destinatario = leerCampoObligatorio(scanner, "Destinatario: ");
                            String direccion = leerCampoObligatorio(scanner, "Dirección de entrega: ");
                            String telefono;
                            do {
                                telefono = leerCampoObligatorio(scanner, "Teléfono de contacto (10 dígitos): ");
                                if (!ValidadorUtils.esTelefonoValido(telefono)) {
                                    System.out.println("Teléfono inválido.");
                                    esperar(1000);
                                    telefono = null;
                                }
                            } while (telefono == null);
                            Paquete paquete = new Paquete(codigo, descripcion, peso, destinatario, direccion, telefono);
                            PaqueteManager.registrarPaquete(paquete);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        esperar(1500);
                    }
                    case 5 -> {
                        System.out.println("== Crear Ruta de Entrega ==");
                        String codigo = leerCampoObligatorio(scanner, "Código de ruta: ");
                        if (RutaManager.existeRuta(codigo)) {
                            System.out.println("Ya existe una ruta con ese código");
                            esperar(1500);
                            break;
                        }
                        String fecha = leerFechaValida(scanner, "Fecha (dd/mm/aaaa): ");
                        String placaVehiculo = leerCampoObligatorio(scanner, "Placa del vehículo: ");
                        if (!AsignacionManager.vehiculoYaAsignado(placaVehiculo)) {
                            System.out.println("El vehículo no está asignado a ningún conductor.");
                            esperar(1500);
                            break;
                        }
                        String cedulaConductor = AsignacionManager.obtenerConductorAsignado(placaVehiculo);
                        if (cedulaConductor == null) {
                            System.out.println("No se encontró conductor asignado a esta placa.");
                            esperar(1500);
                            break;
                        }
                        System.out.print("Códigos de paquetes (separados por coma): ");
                        List<String> codigosPaquetes = Arrays.asList(scanner.nextLine().split(","));
                        if (!RutaManager.verificarPaquetesExistentes(codigosPaquetes)) {
                            System.out.println("Uno o más paquetes no existen.");
                            esperar(1500);
                            break;
                        }
                        Ruta ruta = new Ruta(codigo, fecha, placaVehiculo, cedulaConductor, codigosPaquetes);
                        RutaManager.crearRuta(ruta);
                        esperar(1500);
                    }
                    case 6 -> {
                        System.out.println("== Iniciar Rastreo de Vehículo ==");
                        String codRuta = leerCampoObligatorio(scanner, "Código de la ruta: ");
                        RastreoManager.iniciarRastreo(codRuta);
                        esperar(1500);
                    }
                    case 7 -> {
                        System.out.println("== Registrar Evento de Ruta ==");
                        String codRuta = leerCampoObligatorio(scanner, "Código de ruta: ");
                        String tipo = leerCampoObligatorio(scanner, "Tipo de evento: ");
                        EventoRutaManager.registrarEvento(codRuta, tipo, "");
                        esperar(1500);
                    }
                    case 8 -> {
                        System.out.println("== Consultar Historial de Vehículo ==");
                        String placaHist = leerCampoObligatorio(scanner, "Placa del vehículo: ");
                        HistorialManager.consultarHistorialVehiculo(placaHist);
                        esperar(1500);
                    }
                    case 9 -> {
                        System.out.println("== Generar Reporte de Entregas por Ruta ==");
                        String cod = leerCampoObligatorio(scanner, "Código de ruta: ");
                        ReporteRutaManager.generarReporteRuta(cod);
                        esperar(1500);
                    }
                    case 0 -> {
                        System.out.println("Saliendo del sistema...");
                        esperar(1500);
                    }
                    default -> {
                        System.out.println("Opción inválida\n");
                        esperar(1500);
                    }
                }
            } while (opcion != 0);
        }
    }

    private static void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static String leerCampoObligatorio(Scanner scanner, String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("Este campo es obligatorio.");
                esperar(1000);
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    private static String leerFechaValida(Scanner scanner, String mensaje) {
        String entrada;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        while (true) {
            System.out.print(mensaje);
            entrada = scanner.nextLine();
            try {
                sdf.parse(entrada);
                return entrada;
            } catch (ParseException e) {
                System.out.println("Formato de fecha inválido. Usa dd/mm/aaaa");
                esperar(1000);
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
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
    }

    private static void registrarVehiculo(Scanner scanner) {
        System.out.println("== Registrar Vehículo ==");
        try {
            String placa;
            do {
                placa = leerCampoObligatorio(scanner, "Placa (AAA-0000): ");
                if (!ValidadorUtils.esPlacaValida(placa)) {
                    System.out.println("Placa inválida. Formato esperado: AAA-0000");
                    esperar(1000);
                    placa = null;
                }
            } while (placa == null);

            String modelo = leerCampoObligatorio(scanner, "Modelo: ");
            String tipo = leerCampoObligatorio(scanner, "Tipo: ");

            double capacidad = 0;
            while (true) {
                try {
                    System.out.print("Capacidad de carga (kg): ");
                    capacidad = Double.parseDouble(scanner.nextLine());
                    if (capacidad <= 0) {
                        System.out.println("Debe ser un valor positivo.");
                        esperar(1000);
                    } else break;
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido.");
                    esperar(1000);
                }
            }

            String estado;
            do {
                estado = leerCampoObligatorio(scanner, "Estado operativo (Operativo/Inoperativo): ");
                if (!estado.equalsIgnoreCase("Operativo") && !estado.equalsIgnoreCase("Inoperativo")) {
                    System.out.println("Estado inválido. Debe ser 'Operativo' o 'Inoperativo'.");
                    esperar(1000);
                    estado = null;
                }
            } while (estado == null);

            Vehiculo v = new Vehiculo(placa, modelo, tipo, capacidad, estado);
            VehiculoManager.registrarVehiculo(v);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        esperar(1500);
    }

    private static void registrarConductor(Scanner scanner) {
        System.out.println("== Registrar Conductor ==");
        try {
            String nombre = leerCampoObligatorio(scanner, "Nombre: ");

            String cedula;
            do {
                cedula = leerCampoObligatorio(scanner, "Cédula (10 dígitos): ");
                if (!ValidadorUtils.esCedulaValida(cedula)) {
                    System.out.println("Cédula inválida. Debe contener exactamente 10 dígitos");
                    esperar(1000);
                    cedula = null;
                } else if (ConductorManager.existeConductorPorCedula(cedula)) {
                    System.out.println("Ya existe un conductor con esa cédula");
                    esperar(1000);
                    cedula = null;
                }
            } while (cedula == null);

            String licencia;
            do {
                licencia = leerCampoObligatorio(scanner, "Licencia (A, A1, B, C1, C, D1, D, E1, E, F, G): ");
                if (!ValidadorUtils.esLicenciaValida(licencia)) {
                    System.out.println("Licencia inválida. Clasificaciones válidas: A, A1, B, C1, C, D1, D, E1, E, F, G");
                    esperar(1000);
                    licencia = null;
                }
            } while (licencia == null);

            String telefono;
            do {
                telefono = leerCampoObligatorio(scanner, "Teléfono (10 dígitos): ");
                if (!ValidadorUtils.esTelefonoValido(telefono)) {
                    System.out.println("Teléfono inválido. Debe contener exactamente 10 dígitos");
                    esperar(1000);
                    telefono = null;
                }
            } while (telefono == null);

            String correo;
            do {
                correo = leerCampoObligatorio(scanner, "Correo: ");
                if (!ValidadorUtils.esCorreoValido(correo)) {
                    System.out.println("Correo inválido. Ejemplo: nombre@dominio.com");
                    esperar(1000);
                    correo = null;
                }
            } while (correo == null);

            Conductor conductor = new Conductor(nombre, cedula, licencia, telefono, correo);
            ConductorManager.registrarConductor(conductor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        esperar(1500);
    }
}
