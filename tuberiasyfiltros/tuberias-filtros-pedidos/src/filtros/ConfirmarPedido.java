package filtros;

import entidades.Estado;
import entidades.Pedido;

public class ConfirmarPedido implements Filtro {

    @Override
    public Pedido procesar(Pedido pedido) {
        // Actualizamos el estado del pedido a PROCESADO para confirmarlo
        pedido.setEstado(Estado.PROCESADO);
        
        return pedido;
    }
}
