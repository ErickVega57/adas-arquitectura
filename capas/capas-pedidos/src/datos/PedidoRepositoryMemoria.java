package datos;

import modelo.entidades.Pedido;

import java.util.*;

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
        if(id > almacenamiento.size() || id < 1){
            throw new NoSuchElementException("Pedido no encontrado: " + id);
        }

        return almacenamiento.get(id);
    }


    public List<Pedido> listarTodos() {
        return new ArrayList<>(almacenamiento.values());
    }
}
