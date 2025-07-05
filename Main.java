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
                System.out.println("Registrar Vehiculo:");
                System.out.print("Placa: ");
                String placa = scanner.next();
                System.out.print("Modelo: ");
                String modelo = scanner.next();
                System.out.print("Tipo: ");
                String tipo = scanner.next();
                System.out.print("Capacidad de Carga: ");
                String capacidadCarga = scanner.next();
                System.out.print("Estado Operativo: ");
                String estadoOperativo = scanner.next();
                Vehiculo vehiculo = new Vehiculo(placa, modelo, tipo, capacidadCarga, estadoOperativo);
                Vehiculo.almacenarVehiculo(vehiculo);
                System.out.println("Vehiculo registrado exitosamente.");
            
            case 2:
                // Lógica para registrar conductor
                break;
            case 3:
                // Lógica para asignar vehículo a conductor
                break;
            case 4:
                // Lógica para registrar paquete a entrega
                break;
            case 5:
                // Lógica para crear ruta de entrega
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
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    //ignorar excepcion
                }
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
}