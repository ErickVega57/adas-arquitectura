package datos;
import modelo.entidades.Pedido;

public interface PedidoRepository {
    int guardar(Pedido pedido);
    Pedido buscarPorId(int id);
}
