package datos;

import modelo.entidades.Pedido;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoRepositoryMemoria implements PedidoRepository {
    
    private final Map<Integer, Pedido> almacenamiento = new HashMap<>();
    private int contadorId = 1;

    @Override
    public int guardar(Pedido pedido) {

        almacenamiento.put(contadorId, pedido);
        return contadorId++;
    }

    @Override
    public Pedido buscarPorId(int id) {
        return almacenamiento.get(id);
    }


    public List<Pedido> listarTodos() {
        return new ArrayList<>(almacenamiento.values());
    }
}
