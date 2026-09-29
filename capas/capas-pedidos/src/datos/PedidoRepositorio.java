package datos;

import modelo.entidades.Pedido;
import java.util.List;

public interface PedidoRepositorio {
    int guardar(Pedido pedido);
    Pedido buscarPorId(int id);
    List<Pedido> listarTodos();
}
