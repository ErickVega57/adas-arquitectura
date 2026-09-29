package presentacion;

import datos.PedidoRepository;
import logicanegocio.servicio.PedidoService;
import modelo.entidades.Pedido;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scanner;
    private final PedidoUI pedidoUI;

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
                pedidoUI.registrarPedido();
                break;
            case 2:
                pedidoUI.consultarPedido();
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
            try{
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }
            ejecutarOpciones(opcion);
        }while(opcion != 3);
    }

    public static void main(String[] args) {

        PedidoRepository repositorio = new PedidoRepository() {
            private int siguiente = 1;
            @Override
            public int guardar(Pedido pedido) {
                return siguiente++;
            }

            @Override
            public Pedido buscarPorId(int id) {
                return null;
            }

            @Override
            public List<Pedido> listarTodos() {
                return List.of();
            }
        };

        PedidoService servicio = new PedidoService(repositorio);

        Scanner sc = new Scanner(System.in);
        PedidoUI pedidoUI = new PedidoUI(servicio,sc);

        MenuPrincipal menuPrincipal = new MenuPrincipal(sc, pedidoUI);

        menuPrincipal.ejecutar();
        sc.close();
    }
}
