package datos;

import modelo.entidades.Pedido;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoRepositoryMemoria implements PedidoRepository {
    
    private Map<Integer, Pedido> almacenamiento = new HashMap<>();
    private int contadorId = 1;

    @Override
    public int guardar(Pedido pedido) {
        if (pedido.getId() == 0) {
            pedido.setId(contadorId++);
        }
        almacenamiento.put(pedido.getId(), pedido);
        return pedido.getId();
    }

    @Override
    public Pedido buscarPorId(int id) {
        return almacenamiento.get(id);
    }

    @Override
    public List<Pedido> listarTodos() {
        return new ArrayList<>(almacenamiento.values());
    }
}
