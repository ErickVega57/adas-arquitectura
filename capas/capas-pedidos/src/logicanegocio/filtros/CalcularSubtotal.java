package logicanegocio.filtros;

import modelo.entidades.Pedido;
import modelo.entidades.Producto;

public class CalcularSubtotal implements Filtro {
    @Override
    public Pedido procesar(Pedido pedido) {

        double subtotal = 0;

        for (Producto producto : pedido.getListaDeProductos()){
            subtotal += producto.getPrecioDeProducto() * producto.getCantidadSolicitada();
        }

        pedido.setSubtotal(subtotal);

        return pedido;
    }
}
