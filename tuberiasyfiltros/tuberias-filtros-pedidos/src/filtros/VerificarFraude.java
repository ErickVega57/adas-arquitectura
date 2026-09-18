package filtros;

import entidades.Pedido;

public class VerificarFraude implements Filtro {

    @Override
    public Pedido procesar(Pedido pedido) {
        // Regla del reto: Si el subtotal supera los $5,000, se marca para revisión.
        if (pedido.getSubtotal() > 5000.0) {
            pedido.setPosibleFraude(true); 
        }
        
        return pedido;
    }
}
