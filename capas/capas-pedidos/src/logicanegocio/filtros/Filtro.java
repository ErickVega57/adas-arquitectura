package logicanegocio.filtros;


import modelo.entidades.Pedido;

public interface Filtro {
    Pedido procesar(Pedido pedido);
}
