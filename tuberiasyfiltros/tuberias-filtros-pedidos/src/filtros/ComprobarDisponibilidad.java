package filtros;

import entidades.Pedido;
import entidades.Producto;

public class ComprobarDisponibilidad implements Filtro{
    @Override
    public Pedido procesar(Pedido pedido) {
        for(Producto producto : pedido.getListaDeProductos()){
            if(producto.getCantidadSolicitada() > producto.getExistencia()){
                throw new IllegalArgumentException("No hay suficiente existencia de " + producto.getNombreDeProducto());
            }
        }
        return pedido;
    }
}
