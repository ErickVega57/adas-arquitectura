package presentacion;

import logicanegocio.servicio.PedidoService;
import modelo.entidades.Pedido;
import modelo.entidades.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PedidoUI {

    private final PedidoService pedidoService;
    private final Scanner scanner;

    public PedidoUI(PedidoService pedidoService, Scanner scanner){
        //Servicio
        this.pedidoService = pedidoService;
        this.scanner = scanner;
    }

    // Menú: Registrar Pedido
    protected void registrarPedido() {
        try {
            System.out.println("\n--- REGISTRAR PEDIDO ---");

            System.out.print("Nombre del cliente: ");
            String cliente = scanner.nextLine();

            List<Producto> productos = new ArrayList<>();
            String continuar;

            //añadir productos al pedido
            do {
                System.out.print("Nombre del producto: ");
                String nombre = scanner.nextLine();

                System.out.print("Precio: ");
                double precio = Double.parseDouble(scanner.nextLine());

                System.out.print("Cantidad: ");
                int cantidad = Integer.parseInt(scanner.nextLine());

                System.out.print("Elementos en existencia: ");
                int enExistencia = Integer.parseInt(scanner.nextLine());

                productos.add(new Producto(nombre, precio, cantidad, enExistencia));

                System.out.print("¿Agregar otro producto? (s/n): ");
                continuar = scanner.nextLine().trim().toLowerCase();

            } while (continuar.equals("s"));

            Pedido pedido = new Pedido(cliente, productos);

            int id = pedidoService.registrarPedido(pedido);
            System.out.println("\nPedido registrado con ID: " + id);
            mostrarPedido(pedido);

        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al procesar el pedido: " + e.getMessage());
        }
    }

    protected void consultarPedido() {
        try {
            System.out.println("\n--- CONSULTAR PEDIDO ---");
            System.out.print("ID del pedido: ");
            int id = Integer.parseInt(scanner.nextLine());

            Pedido pedido = pedidoService.buscarPorId(id);
            System.out.println("\nPedido encontrado:");
            mostrarPedido(pedido);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarPedido(Pedido pedido){
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("Estado: " + pedido.getEstado());

        System.out.println("Productos:");
        for (Producto p: pedido.getListaDeProductos()) {
            mostrarProducto(p);
        }
        System.out.println("   Subtotal:  " + String.format("%.2f",pedido.getSubtotal()));
        System.out.println("   Descuento: " + String.format("%.2f",pedido.getDescuento()));
        System.out.println("   Impuestos: " + String.format("%.2f",pedido.getImpuestos()));
        System.out.println("   Total:     " + String.format("%.2f",pedido.getTotal()));
        System.out.println();
    }

    private void mostrarProducto(Producto producto){
        System.out.printf( "[%s, %.2f, %d, %d]\n",
        producto.getNombreDeProducto(), producto.getPrecioDeProducto(),
        producto.getCantidadSolicitada(), producto.getExistencia());
    }
}
