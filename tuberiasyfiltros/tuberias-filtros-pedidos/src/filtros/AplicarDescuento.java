package filtros;

import entidades.Pedido;

public class AplicarDescuento implements Filtro{

    @Override
    public Pedido procesar(Pedido pedido) {
        double subtotal = pedido.getSubtotal();
        pedido.setDescuento(subtotal >= 1000 ? subtotal * 0.10 : 0);
        return pedido;
    }

}
