package logicanegocio.filtros;

import modelo.entidades.Pedido;
import modelo.entidades.Estado;

public class ConfirmarPedido implements Filtro {

    @Override
    public Pedido procesar(Pedido pedido) {
        // Actualizamos el estado del pedido a PROCESADO para confirmarlo
        pedido.setEstado(Estado.PROCESADO);
        
        return pedido;
    }
}
