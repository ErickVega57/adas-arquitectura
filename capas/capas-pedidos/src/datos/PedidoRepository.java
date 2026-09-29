package datos;

import modelo.entidades.Pedido;
import java.util.List;

public interface PedidoRepository {
    int guardar(Pedido pedido);
    Pedido buscarPorId(int id);
}
