import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do{
            mostrarMenu();
            System.out.println("Selecciona una opcion:");
            opcion = scanner.nextInt();
        switch(opcion){
            case 1:
                System.out.println("-Registrar Vehiculo-");
                System.out.print("Placa: ");
                String placa = scanner.next();
                scanner.nextLine();
                System.out.print("Modelo: ");
                String modelo = scanner.next();
                scanner.nextLine();
                System.out.print("Tipo: ");
                String tipo = scanner.next();
                scanner.nextLine();
                System.out.print("Capacidad de Carga: ");
                String capacidadCarga = scanner.next();
                scanner.nextLine();

                //Estado Operativo y Validacion de entrada y conversion a booleano
                System.out.print("¿El vehículo está operativo? (1 = Sí, 0 = No): ");
                while(!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Por favor, ingresa 1 para Sí o 0 para No.");
                    scanner.next();
                }
                int intestadoOperativo = scanner.nextInt();
                while(intestadoOperativo != 0 && intestadoOperativo != 1) {
                    System.out.println("Entrada inválida. Por favor, ingresa 1 para Sí o 0 para No.");
                    intestadoOperativo = scanner.nextInt();
                }
                boolean estadoOperativo = (intestadoOperativo == 1);
                scanner.nextLine();

                Vehiculo vehiculo = new Vehiculo(placa, modelo, tipo, capacidadCarga, estadoOperativo);
                Vehiculo.almacenarVehiculo(vehiculo);
                System.out.print("Vehiculo registrado exitosamente.");
                esperar(1000);
            break;

            case 2:
                System.out.println("-Registrar Conductor-");
                System.out.print("Nombre: ");
                String nombre = scanner.next();
                scanner.nextLine();
                System.out.print("Cedula: ");
                String cedula = scanner.next();
                scanner.nextLine();
                System.out.print("Licencia: ");
                String licencia = scanner.next();
                scanner.nextLine();
                System.out.print("Telefono: ");
                String telefono = scanner.next();
                scanner.nextLine();
                System.out.print("Correo: ");
                String correo = scanner.next();
                scanner.nextLine();
                Conductor conductor = new Conductor(nombre, cedula, licencia, telefono, correo);
                conductor.almacenarConductor(conductor);
                System.out.print("Conductor registrado exitosamente.");
                esperar(1000);
            break;

            case 3:
                System.out.println("-Asignar Vehiculo a Conductor-");
                System.out.print("Ingrese la placa del vehículo: ");
                String placaVehiculo = scanner.next();
                Vehiculo vehiculoBuscado = Vehiculo.buscarVehiculo(placaVehiculo);
                if (vehiculoBuscado == null) {
                    System.out.println("Vehículo no encontrado.");
                    esperar(1000);
                    break;
                }else if(!vehiculoBuscado.isEstadoOperativo()) {
                    System.out.println("El vehículo no está operativo.");
                    esperar(1000);
                    break;
                }else{
                    Conductor conductorLibre = Conductor.conductorLibre();
                    if (conductorLibre != null) {
                        vehiculoBuscado.asignarConductor(conductorLibre);
                        System.out.println("Vehículo asignado al conductor " + conductorLibre.getNombre() + " con cédula " + conductorLibre.getCedula() + ".");
                    } else {
                        System.out.println("No hay conductores libres disponibles.");
                    }
                }
                scanner.nextLine();
                esperar(1000);
            break;    

            case 4:
                System.out.println("-Registrar Paquete-");
                System.out.print("ID: ");
                String id = scanner.next();
                scanner.nextLine();
                System.out.print("Descripcion: ");
                String descripcion = scanner.next();
                scanner.nextLine();
                System.out.print("Peso: ");
                String peso = scanner.next();
                scanner.nextLine();
                System.out.print("Destinatario: ");
                String destinatario = scanner.next();
                scanner.nextLine();
                System.out.print("Direccion: ");
                String direccion = scanner.next();
                scanner.nextLine();
                System.out.print("Telefono de contacto: ");
                int telefono_contacto = scanner.nextInt();
                scanner.nextLine();
                Paquete paquete = new Paquete(id,descripcion,peso,destinatario,direccion,telefono_contacto);
                Paquete.almacenarPaquete(paquete);
                System.out.print("Paquete registrado exitosamente.");
                esperar(1000);
                break;
            case 5:
                try {
                    System.out.print("Fecha de la ruta (dd/mm/aaaa): ");
                    String fechaa = scanner.nextLine();
                    System.out.print("Placa del vehículo asignado: ");
                    String placaa = scanner.nextLine();
                    System.out.print("Cédula del conductor asignado: ");
                    String cedulaa = scanner.nextLine();
                    System.out.println("Ingrese los códigos de paquetes (escriba FIN para terminar):");
                    List<String> codigosPaquetes = new ArrayList<>();
                    while (true) {
                        String codigo = scanner.nextLine();
                        if (codigo.equalsIgnoreCase("FIN")){
                            break;
                        }else{
                            codigosPaquetes.add(codigo);
                        }
                    }
                    Paquete.crearRuta(fechaa, placaa, cedulaa, codigosPaquetes);
                    System.out.println("Ruta creada y guardada con éxito.");
                }catch (Exception e) {
                    System.out.println("Error al crear ruta: " + e.getMessage());
                }
            break;
            case 6:
                    try {
                        System.out.print("Placa del vehículo a rastrear: ");
                        String placaa = scanner.nextLine();
                        System.out.print("Fecha de la ruta (dd/mm/aaaa): ");
                        String fecha = scanner.nextLine();
                        System.out.print("Hora de salida (hh:mm): ");
                        String hora = scanner.nextLine();

                        String registro = fecha + "," + placaa + "," + hora;
                        //guardarEnArchivo("rastreo.txt", registro);

                        System.out.println("Rastreo iniciado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("Error al iniciar rastreo: " + e.getMessage());
                    }
            break;
            case 7:
                try {
                    System.out.println("--- Registrar Evento de Ruta ---");
                    System.out.print("Fecha (dd/mm/aaaa): ");
                    String fecha = scanner.nextLine();
                    System.out.print("Placa del vehículo: ");
                    String placaa = scanner.nextLine();
                    System.out.println("Tipo de evento:");
                    System.out.println("1. Parada técnica");
                    System.out.println("2. Entrega realizada");
                    System.out.println("3. Incidente en vía");
                    System.out.println("4. Retorno a base");
                    System.out.print("Seleccione tipo (1-4): ");
                    int tipoo = scanner.nextInt(); 
                    scanner.nextLine();
                    String tipoEvento = switch (tipoo) {
                        case 1 -> "Parada técnica";
                        case 2 -> "Entrega realizada";
                        case 3 -> "Incidente en vía";
                        case 4 -> "Retorno a base";
                        default -> "Otro";
                    };
                    System.out.print("Hora del evento (hh:mm): ");
                    String hora = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcionn = scanner.nextLine();

                    String evento = fecha + "," + placaa + "," + tipoEvento + "," + hora + "," + descripcionn;
                    //guardarEnArchivo("eventosRuta.txt", evento);
                    System.out.println("Evento registrado exitosamente.");
                } catch (Exception e) {
                    System.out.println("Error al registrar el evento: " + e.getMessage());
                }
            break; 
            case 8:

        //Codigo con CHAT GPT XD ya me dio pereza esta parte asi que mñana la reviso
        //el codigo de chatgpt es para tener una referencia :v
       /*try {
            System.out.print("Ingrese la placa del vehículo: ");
            String placa = scanner.nextLine();

            List<String> rutas = new ArrayList<>();
            List<String> eventos = new ArrayList<>();
            int entregas = 0;

            try (Scanner rutaScanner = new Scanner(new File("rutas.txt"))) {
                while (rutaScanner.hasNextLine()) {
                    String linea = rutaScanner.nextLine();
                    if (linea.contains(placa)) {
                        rutas.add(linea);
                        String[] partes = linea.split(",");
                        if (partes.length >= 4) {
                            entregas += partes[3].split("\\|").length;
                        }
                    }
                }
            }

            try (Scanner eventoScanner = new Scanner(new File("eventosRuta.txt"))) {
                while (eventoScanner.hasNextLine()) {
                    String linea = eventoScanner.nextLine();
                    if (linea.contains(placa)) {
                        eventos.add(linea);
                    }
                }
            }

            System.out.println("--- Historial del Vehículo " + placa + " ---");
            System.out.println("Rutas:");
            rutas.forEach(System.out::println);
            System.out.println("\nEventos:");
            eventos.forEach(System.out::println);
            System.out.println("\nTotal de entregas realizadas: " + entregas);

            try (FileWriter fw = new FileWriter("historialVehiculos.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write("Placa: " + placa);
                bw.newLine();
                bw.write("Rutas:");
                bw.newLine();
                for (String r : rutas) {
                    bw.write(r);
                    bw.newLine();
                }
                bw.write("Eventos:");
                bw.newLine();
                for (String ev : eventos) {
                    bw.write(ev);
                    bw.newLine();
                }
                bw.write("Total entregas: " + entregas);
                bw.newLine();
                bw.write("------------------------------");
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al consultar historial: " + e.getMessage());
        }
    }

    public static void guardarEnArchivo(String nombreArchivo, String linea) {
        try (FileWriter fw = new FileWriter(nombreArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    } 
                break;*/
            case 9:

            //LO MISMO QUE EL 8 :V
    /*public static void generarReporteRuta() {
        try {
            System.out.print("Ingrese la fecha de la ruta (dd/mm/aaaa): ");
            String fecha = scanner.nextLine();
            System.out.print("Ingrese la placa del vehículo: ");
            String placa = scanner.nextLine();

            String lineaRuta = null;
            try (Scanner rutasScanner = new Scanner(new File("rutas.txt"))) {
                while (rutasScanner.hasNextLine()) {
                    String linea = rutasScanner.nextLine();
                    if (linea.startsWith(fecha + "," + placa + ",")) {
                        lineaRuta = linea;
                        break;
                    }
                }
            }

            if (lineaRuta == null) {
                System.out.println("Ruta no encontrada.");
                return;
            }

            String[] partes = lineaRuta.split(",");
            String cedula = partes[2];
            String[] codigos = partes[3].split("\\|");
            int entregas = 0, fallidas = 0;
            List<String> eventos = new ArrayList<>();
            String horaInicio = "";
            String horaFin = "";

            try (Scanner eventosScanner = new Scanner(new File("eventosRuta.txt"))) {
                while (eventosScanner.hasNextLine()) {
                    String linea = eventosScanner.nextLine();
                    if (linea.contains(fecha) && linea.contains(placa)) {
                        eventos.add(linea);
                        if (linea.contains("Entrega realizada")) entregas++;
                        if (linea.contains("Incidente")) fallidas++;
                        String[] ev = linea.split(",");
                        if (horaInicio.equals("") || ev[3].compareTo(horaInicio) < 0) horaInicio = ev[3];
                        if (horaFin.equals("") || ev[3].compareTo(horaFin) > 0) horaFin = ev[3];
                    }
                }
            }

            String resumen = "--- Reporte de Ruta ---\n" +
                    "Fecha: " + fecha + "\n" +
                    "Vehículo: " + placa + "\n" +
                    "Conductor (cédula): " + cedula + "\n" +
                    "Entregas exitosas: " + entregas + "\n" +
                    "Entregas fallidas: " + fallidas + "\n" +
                    "Tiempo total en ruta: " + horaInicio + " - " + horaFin + "\n" +
                    "Eventos registrados: " + eventos.size();

            System.out.println(resumen);

            guardarEnArchivo("reportes.txt", resumen.replace("\n", " | "));

        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }

    public static void guardarEnArchivo(String nombreArchivo, String linea) {
        try (FileWriter fw = new FileWriter(nombreArchivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }*/
                break;
            case 10:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción no válida");
                scanner.nextLine();
                
                break;
        }
    } while(opcion != 10);
        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("Menu de opciones:");
        System.out.println("1. Registrar Vehiculo");
        System.out.println("2. Registrar Conductor");
        System.out.println("3. Asignar Vehiculo a Conductor");
        System.out.println("4. Registrar Paquete a Entrega");
        System.out.println("5. Crear Ruta de Entrega");
        System.out.println("6. Iniciar Rastro de Vehiculo");
        System.out.println("7. Registrar Evento de Ruta");
        System.out.println("8. Consultar Historial de Vehiculo");
        System.out.println("9. Generar Reporte de Entrega por Ruta");
        System.out.println("10. Salir");
    }
    public static void esperar(int milis){
        try{
                    Thread.sleep(milis);
                } catch (InterruptedException e) {
                    //ignorar excepcion
                }
    }
}