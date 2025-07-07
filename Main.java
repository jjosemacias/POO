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
                    String fecha = scanner.nextLine();
                    System.out.print("Placa del vehículo asignado: ");
                    String placaa = scanner.nextLine();
                    System.out.print("Cédula del conductor asignado: ");
                    String cedulaa = scanner.nextLine();

                    List<String> codigosPaquetes = new ArrayList<>();
                    System.out.println("Ingrese los códigos de paquetes (escriba FIN para terminar):");
                    while (true) {
                        String codigo = scanner.nextLine();
                        if (codigo.equalsIgnoreCase("FIN")) break;
                        codigosPaquetes.add(codigo);
                    }

                    Paquete.crearRuta(fecha, placaa, cedulaa, codigosPaquetes);
                    System.out.println("Ruta creada y guardada con éxito.");

                } catch (Exception e) {
                    System.out.println("Error al crear ruta: " + e.getMessage());
                }
    
                break;
            case 6:
                // Lógica para iniciar rastro de vehículo
                break;
            case 7:
                // Lógica para registrar evento de ruta
                break; 
            case 8:
                // Lógica para consultar historial de vehículo 
                break;
            case 9:
                // Lógica para generar reporte de entrega por ruta
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
    public static void esperar(int ms){
        try{
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    //ignorar excepcion
                }
    }
}