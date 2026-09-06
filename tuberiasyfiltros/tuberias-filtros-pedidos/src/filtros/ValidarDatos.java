package filtros;

import entidades.Pedido;

public class ValidarDatos implements Filtro{

    @Override
    public Pedido procesar(Pedido pedido) {
        if(pedido.getCliente() == null || pedido.getCliente().isEmpty()){
            throw new IllegalArgumentException("El pedido no tiene cliente");
        }
        if(pedido.getListaDeProductos() == null || pedido.getListaDeProductos().isEmpty()){
            throw new IllegalArgumentException("El pedido no tiene productos");
        }
        return pedido;
    }
}
