package filtros;

import entidades.Pedido;

public interface Filtro {
    Pedido procesar(Pedido pedido);
}
