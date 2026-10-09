package vista;

import modelo.Pedido;
import modelo.PedidoModelo;

public class PedidoVistaResumida extends PedidoVista{

    public PedidoVistaResumida(PedidoModelo modelo) {
        super(modelo);
    }

    @Override
    public void mostrarPedido(Pedido pedido) {
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("   Total:     " + String.format("%.2f",pedido.getTotal()));
        System.out.println("Estado: " + pedido.getEstado());
        System.out.println();
    }
}
