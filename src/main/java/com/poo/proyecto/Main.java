package com.poo.proyecto;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import com.poo.proyecto.models.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do{
            mostrarMenu();
            System.out.println("Selecciona una opcion:");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

        switch (opcion){
            case 1 ->{
                System.out.println("-Registrar Vehiculo-");
                Vehiculo vehiculo = new Vehiculo("","","",0,false);
                while (true) {
                    System.out.print("Placa: ");
                    String placa = scanner.nextLine();
                    if (vehiculo.setPlaca(placa)) break;
                    esperar(1000);
                }
                while (true) {
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    if (vehiculo.setModelo(modelo)) break;
                    esperar(1000);
                }
                while (true) {
                    System.out.print("Tipo (Camioneta, Camion, Furgoneta): ");
                    String tipo = scanner.nextLine();
                    if (vehiculo.setTipo(tipo)) break;
                    esperar(1000);
                }
                while (true) {
                    System.out.print("Capacidad de Carga (en kg): ");
                    String capacidadCargaStr = scanner.nextLine();
                    if (vehiculo.setCapacidadCarga(Integer.parseInt(capacidadCargaStr))) break;
                    esperar(1000);
                }
                while (true) {
                    System.out.print("Estado Operativo (1 = Operativo, 0 = No Operativo): ");
                    String estadoStr = scanner.nextLine();
                    if (estadoStr.equals("1")) {
                        vehiculo.setEstadoOperativo(true);
                        break;
                    } else if (estadoStr.equals("0")) {
                        vehiculo.setEstadoOperativo(false);
                        break;
                    } else {
                        System.out.println("Entrada inválida. Debe ingresar 1 para Operativo o 0 para No Operativo.");
                        esperar(1000);  
                    }
                }
                vehiculo.almacenarVehiculo();
                System.out.println("Vehículo registrado exitosamente.");
                esperar(2000);
                scanner.nextLine(); // Limpiar el buffer del scanner
            }

            case 2 -> {
                System.out.println("-Registrar Conductor-");
                Conductor conductor = new Conductor("","", "", "", "");
                System.out.print("Nombre: ");
                conductor.setNombre(scanner.nextLine());
                while (true) {
                    System.out.print("Cédula: ");   
                    String cedula = scanner.nextLine();   
                    if (conductor.setCedula(cedula)) break;   
                    esperar(1000);
                }
                while (true) {
                    System.out.print("Licencia (A-F): ");
                    String licencia = scanner.nextLine();
                    if (conductor.setLicencia(licencia)) break;
                    esperar(1000);
                }
                while (true) {
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    if (conductor.setTelefono(telefono)) break;
                    esperar(1000);
                }
                while (true) {
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();
                    if (conductor.setCorreo(correo)) break;
                    esperar(1000);
                }
                conductor.almacenarConductor();
                System.out.println("Conductor registrado exitosamente.");
                esperar(2000);
                scanner.nextLine(); // Limpiar el buffer del scanner
                }

            case 3 -> {
                  System.out.println("-Asignar Vehiculo a Conductor-");
                  System.out.print("Ingrese la placa del vehículo: ");
                  String placaVehiculo = scanner.next();
                  Vehiculo vehiculoBuscado = Vehiculo.buscarVehiculoRegistrado(placaVehiculo);
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
                }

            case 4 -> {
                System.out.println("-Registrar Paquete-");
                Paquete paquete = new Paquete("","",0,"","","");
                while(true){
                    System.out.print("ID del paquete: ");
                    String id = scanner.nextLine();
                    if(paquete.setId(id)) break;
                }
                while(true){
                    System.out.print("Descripción del paquete: ");
                    String descripcion = scanner.nextLine();
                    if(paquete.setDescripcion(descripcion)) break;
                }
                while(true){
                    System.out.print("Peso del paquete [kg]: ");
                    int peso = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer del scanner
                    if(paquete.setPeso(peso)) break;
                }
                while(true){
                    System.out.print("Destinatario del paquete: ");
                    String destinatario = scanner.nextLine();
                    if(paquete.setDestinatario(destinatario)) break;
                }
                while(true){
                    System.out.print("Dirección del paquete: ");
                    String direccion = scanner.nextLine();
                    if(paquete.setDireccion(direccion)) break;
                }
                while(true){
                    System.out.print("Teléfono de contacto: ");
                    String telefono = scanner.nextLine();
                    if(paquete.setTelefonoContacto(telefono)) break;
                }
                paquete.almacenarPaquete();
                System.out.print("Paquete registrado exitosamente.");
                scanner.nextLine();
                esperar(1000);
            }
            case 5 -> {
            System.out.println("-Crear Ruta de Entrega-");
            ArrayList<String> codigosPaquetes = new ArrayList<>();
            Ruta ruta = new Ruta("","","",codigosPaquetes);
            while(true){
                System.out.print("Fecha de la ruta (dd/MM/yyyy): ");
                String fecha = scanner.nextLine();
                if (ruta.setFecha(fecha)) break;
            }
            while(true){
                System.out.print("Placa del vehículo asignado: ");
                String placaVehiculo = scanner.nextLine();
                if (ruta.setPlacaVehiculo(placaVehiculo)) break;
            }
            while (true) {
                System.out.print("Ingrese código de paquete (escriba 'fin' para terminar): ");
                String codigo = scanner.nextLine();
                if (codigo.trim().isEmpty()) {
                    System.out.println("El código no puede estar vacío. Intente nuevamente.");
                    continue;
                }

                if (codigo.equalsIgnoreCase("fin")) break;

                if (Paquete.buscarPaqueteRegistrado(codigo)) {
                    codigosPaquetes.add(codigo);
                    System.out.println("Paquete añadido.");
                } else {
                    System.out.println("El código de paquete no está registrado.");

                }
            }
            ruta.setCodigosPaquetes(codigosPaquetes);
            ruta.almacenarRuta();



            //     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            //     sdf.setLenient(false);
            //     String fecha_string_final = "";
            //     boolean fechaValida = false;
            //     while (!fechaValida) {
            //         System.out.print("Ingrese fecha (dd/MM/yyyy): ");
            //         String fechaStr = scanner.next();
            //         scanner.nextLine();

            //         try {
            //             Date fecha = sdf.parse(fechaStr);
            //             fecha_string_final = sdf.format(fecha);
            //             fechaValida = true;
            //         } catch (ParseException e) {
            //             System.out.println("Fecha inválida. Intente nuevamente.");
            //         }
            //     }
            //     System.out.println("Fecha ingresada corectamente");


            //     ///

            //     System.out.print("Ingrese la Placa del vehículo asignado: ");
            //     String placa_c5 = scanner.next();
            //     scanner.nextLine();


                
            //     esperar(1000);
            //     break;
            }
            case 6 -> {
                    // try {
                    //     System.out.print("Placa del vehículo a rastrear: "); //validar vehiculo en rutas.txt
                    //     String placaa = scanner.nextLine();
                    //     System.out.print("Fecha de la ruta (dd/mm/aaaa): ");
                    //     String fecha = scanner.nextLine();
                    //     System.out.print("Hora de salida (hh:mm): ");
                    //     String hora = scanner.nextLine();

                    //     String registro = fecha + "," + placaa + "," + hora;
                    //     //guardarEnArchivo("rastreo.txt", registro);

                    //     System.out.println("Rastreo iniciado exitosamente.");
                    // } catch (Exception e) {
                    //     System.out.println("Error al iniciar rastreo: " + e.getMessage());
                    // }
            }
            case 7 -> {
                // try {
                //     System.out.println("--- Registrar Evento de Ruta ---");
                //     System.out.print("Fecha (dd/mm/aaaa): ");
                //     String fecha = scanner.nextLine();
                //     System.out.print("Placa del vehículo: ");
                //     String placaa = scanner.nextLine();
                //     System.out.println("Tipo de evento:");
                //     System.out.println("1. Parada técnica");
                //     System.out.println("2. Entrega realizada");
                //     System.out.println("3. Incidente en vía");
                //     System.out.println("4. Retorno a base");
                //     System.out.print("Seleccione tipo (1-4): ");
                //     int tipoo = scanner.nextInt(); 
                //     scanner.nextLine();
                //     String tipoEvento = switch (tipoo) {
                //         case 1 -> "Parada técnica";
                //         case 2 -> "Entrega realizada";
                //         case 3 -> "Incidente en vía";
                //         case 4 -> "Retorno a base";
                //         default -> "Otro";
                //     };
                //     System.out.print("Hora del evento (hh:mm): ");
                //     String hora = scanner.nextLine();
                //     System.out.print("Descripción: ");
                //     String descripcionn = scanner.nextLine();

                //     String evento = fecha + "," + placaa + "," + tipoEvento + "," + hora + "," + descripcionn;
                //     //guardarEnArchivo("eventosRuta.txt", evento);
                //     System.out.println("Evento registrado exitosamente.");
                // } catch (Exception e) {
                //     System.out.println("Error al registrar el evento: " + e.getMessage());
                // }
             }
            case 8 -> {

        //Codigo con CHAT GPT XD ya me dio pereza esta parte asi que mñana la reviso
        //el codigo de chatgpt es para tener una referencia :v
    //    try {
    //         System.out.print("Ingrese la placa del vehículo: ");
    //         String placa = scanner.nextLine();

    //         List<String> rutas = new ArrayList<>();
    //         List<String> eventos = new ArrayList<>();
    //         int entregas = 0;

    //         try (Scanner rutaScanner = new Scanner(new File("rutas.txt"))) {
    //             while (rutaScanner.hasNextLine()) {
    //                 String linea = rutaScanner.nextLine();
    //                 if (linea.contains(placa)) {
    //                     rutas.add(linea);
    //                     String[] partes = linea.split(",");
    //                     if (partes.length >= 4) {
    //                         entregas += partes[3].split("\\|").length;
    //                     }
    //                 }
    //             }
    //         }

    //         try (Scanner eventoScanner = new Scanner(new File("eventosRuta.txt"))) {
    //             while (eventoScanner.hasNextLine()) {
    //                 String linea = eventoScanner.nextLine();
    //                 if (linea.contains(placa)) {
    //                     eventos.add(linea);
    //                 }
    //             }
    //         }

    //         System.out.println("--- Historial del Vehículo " + placa + " ---");
    //         System.out.println("Rutas:");
    //         rutas.forEach(System.out::println);
    //         System.out.println("\nEventos:");
    //         eventos.forEach(System.out::println);
    //         System.out.println("\nTotal de entregas realizadas: " + entregas);

    //         try (FileWriter fw = new FileWriter("historialVehiculos.txt", true);
    //              BufferedWriter bw = new BufferedWriter(fw)) {
    //             bw.write("Placa: " + placa);
    //             bw.newLine();
    //             bw.write("Rutas:");
    //             bw.newLine();
    //             for (String r : rutas) {
    //                 bw.write(r);
    //                 bw.newLine();
    //             }
    //             bw.write("Eventos:");
    //             bw.newLine();
    //             for (String ev : eventos) {
    //                 bw.write(ev);
    //                 bw.newLine();
    //             }
    //             bw.write("Total entregas: " + entregas);
    //             bw.newLine();
    //             bw.write("------------------------------");
    //             bw.newLine();
    //         }

    //     } catch (IOException e) {
    //         System.out.println("Error al consultar historial: " + e.getMessage());
    //     }
    // }

    // public static void guardarEnArchivo(String nombreArchivo, String linea) {
    //     try (FileWriter fw = new FileWriter(nombreArchivo, true);
    //          BufferedWriter bw = new BufferedWriter(fw)) {
    //         bw.write(linea);
    //         bw.newLine();
    //     } catch (IOException e) {
    //         System.out.println("Error al guardar en archivo: " + e.getMessage());
    //     }
    // } 
            }
            case 9 -> {

    //         LO MISMO QUE EL 8 :V
    //         public static void generarReporteRuta() {
    //     try {
    //         System.out.print("Ingrese la fecha de la ruta (dd/mm/aaaa): ");
    //         String fecha = scanner.nextLine();
    //         System.out.print("Ingrese la placa del vehículo: ");
    //         String placa = scanner.nextLine();

    //         String lineaRuta = null;
    //         try (Scanner rutasScanner = new Scanner(new File("rutas.txt"))) {
    //             while (rutasScanner.hasNextLine()) {
    //                 String linea = rutasScanner.nextLine();
    //                 if (linea.startsWith(fecha + "," + placa + ",")) {
    //                     lineaRuta = linea;
    //                     break;
    //                 }
    //             }
    //         }

    //         if (lineaRuta == null) {
    //             System.out.println("Ruta no encontrada.");
    //             return;
    //         }

    //         String[] partes = lineaRuta.split(",");
    //         String cedula = partes[2];
    //         String[] codigos = partes[3].split("\\|");
    //         int entregas = 0, fallidas = 0;
    //         List<String> eventos = new ArrayList<>();
    //         String horaInicio = "";
    //         String horaFin = "";

    //         try (Scanner eventosScanner = new Scanner(new File("eventosRuta.txt"))) {
    //             while (eventosScanner.hasNextLine()) {
    //                 String linea = eventosScanner.nextLine();
    //                 if (linea.contains(fecha) && linea.contains(placa)) {
    //                     eventos.add(linea);
    //                     if (linea.contains("Entrega realizada")) entregas++;
    //                     if (linea.contains("Incidente")) fallidas++;
    //                     String[] ev = linea.split(",");
    //                     if (horaInicio.equals("") || ev[3].compareTo(horaInicio) < 0) horaInicio = ev[3];
    //                     if (horaFin.equals("") || ev[3].compareTo(horaFin) > 0) horaFin = ev[3];
    //                 }
    //             }
    //         }

    //         String resumen = "--- Reporte de Ruta ---\n" +
    //                 "Fecha: " + fecha + "\n" +
    //                 "Vehículo: " + placa + "\n" +
    //                 "Conductor (cédula): " + cedula + "\n" +
    //                 "Entregas exitosas: " + entregas + "\n" +
    //                 "Entregas fallidas: " + fallidas + "\n" +
    //                 "Tiempo total en ruta: " + horaInicio + " - " + horaFin + "\n" +
    //                 "Eventos registrados: " + eventos.size();

    //         System.out.println(resumen);

    //         guardarEnArchivo("reportes.txt", resumen.replace("\n", " | "));

    //     } catch (IOException e) {
    //         System.out.println("Error al generar el reporte: " + e.getMessage());
    //     }
    // }

    // public static void guardarEnArchivo(String nombreArchivo, String linea) {
    //     try (FileWriter fw = new FileWriter(nombreArchivo, true);
    //          BufferedWriter bw = new BufferedWriter(fw)) {
    //         bw.write(linea);
    //         bw.newLine();
    //     } catch (IOException e) {
    //         System.out.println("Error al guardar en archivo: " + e.getMessage());
    //     }
    // }
            }
            case 10 -> {
                System.out.println("Saliendo del programa...");
            }
            default -> {
                System.out.println("Opción no válida");
                scanner.nextLine();
            }
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