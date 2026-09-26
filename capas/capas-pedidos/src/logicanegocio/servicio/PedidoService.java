package logicanegocio.servicio;

import datos.PedidoRepository;
import logicanegocio.tuberia.Tuberia;
import modelo.entidades.Pedido;

import java.util.NoSuchElementException;

public class PedidoService {

    private final Tuberia tuberia;
    private final PedidoRepository repositorio;
    public PedidoService(Tuberia tuberia, PedidoRepository repositorio){

        this.tuberia = tuberia;
        this.repositorio = repositorio;
    }

    public int registrarPedido(Pedido pedido) throws IllegalArgumentException{
        /*
        procesa el pedido lanza excepción
        si el pedido no se procesa correctamente
        se maneja en la UI
         */
        procesarPedido(pedido);
        return repositorio.guardar(pedido);
    }

    public Pedido buscarPorId(int id){
        /*
        si no se encuentra lanza excepcion
        se maneja en la UI
         */
        return repositorio.buscarPorId(id);
    }

    private int guardar(Pedido pedido){
        return repositorio.guardar(pedido);
    }

    private Pedido procesarPedido(Pedido pedido){
        return tuberia.recorrer(pedido);
    }
}
