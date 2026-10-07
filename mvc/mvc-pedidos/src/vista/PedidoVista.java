package vista;

import modelo.Pedido;
import modelo.PedidoModelo;
import modelo.Producto;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoVista implements Observer {

    private final Scanner scanner;
    private final PedidoModelo modelo;

    public PedidoVista(PedidoModelo modelo){
        this.scanner = new Scanner(System.in);
        this.modelo = modelo;
    }

    @Override
    public void update() {
        Object dato = modelo.getData();
        if(dato instanceof String){
            mostrarMensaje(dato);
        } else if (dato instanceof  Pedido) {
            mostrarPedido(dato);
        }
    }

    public int mostrarMenu(){
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Registrar pedido");
        System.out.println("2. Consultar pedido por ID");
        System.out.println("3. Salir");
        System.out.println("==========================");
        System.out.print("Selecciona una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public void mostra@Override
    public void update() {

    }rError(String mensaje){
        System.out.println("EROR" + mensaje);
    }

    public void mostrarPedido(Pedido pedido){
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

    public Pedido capturarPedido(){
        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();

        List<Producto> productos = new ArrayList<>();

        String continuar = "n";

        do{
            Producto producto;
            try {
                producto = capturarProducto();
            }catch (NumberFormatException e){
                mostrarError("Error al ingresar pedido");
                continue;
            }
            productos.add(producto);
            System.out.print("¿Agregar otro producto? (s/n): ");
            continuar = scanner.nextLine().trim().toLowerCase();
        }while (continuar.equals("s"));

        return new Pedido(cliente, productos);
    }

    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    private Producto capturarProducto(){
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        System.out.print("Elementos en existencia: ");
        int enExistencia = Integer.parseInt(scanner.nextLine());

        return new Producto(nombre, precio, cantidad, enExistencia);
    }

    private void mostrarProducto(Producto producto){
        System.out.printf( "[%s, %.2f, %d, %d]\n",
                producto.getNombreDeProducto(), producto.getPrecioDeProducto(),
                producto.getCantidadSolicitada(), producto.getExistencia());
    }
}
