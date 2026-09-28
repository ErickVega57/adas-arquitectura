package presentacion;

import logicanegocio.servicio.PedidoService;
import logicanegocio.tuberia.Tuberia;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private Scanner scanner;
    private PedidoUI pedidoUI;

    public MenuPrincipal(Scanner scanner, PedidoUI pedidoUI){
        this.scanner = scanner;
        this.pedidoUI = pedidoUI;
    }

    private void mostrarMenu(){
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Registrar pedido");
        System.out.println("2. Consultar pedido por ID");
        System.out.println("3. Salir");
        System.out.println("==========================");
        System.out.print("Selecciona una opción: ");
    }

    private void ejecutarOpciones(int opcion){
        switch (opcion) {
            case 1:
                //PedidoUI.registrarPedido(servicio, sc);
                System.out.println("1");
                break;
            case 2:
                //PedidoUI.consultarPedido(servicio, sc);
                System.out.println("2");
                break;
            case 3:
                System.out.println("Cerrando el programa...");
                break;
            default:
                System.out.println("Opción inválida. Intenta de nuevo.");
        }
    }

    public void ejecutar(){
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            ejecutarOpciones(opcion);
        }while(opcion != 3);
    }

    public static void main(String[] args) {
        //PedidoRepository repositorio = new PedidoRepository();
        //PedidoService servicio = new PedidoService(repositorio);
        Scanner sc = new Scanner(System.in);
        MenuPrincipal menuPrincipal = new MenuPrincipal(sc, new PedidoUI());
        menuPrincipal.ejecutar();
        sc.close();
    }
}
