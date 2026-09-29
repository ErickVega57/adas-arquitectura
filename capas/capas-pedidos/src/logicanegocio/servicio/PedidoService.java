package logicanegocio.servicio;

import datos.PedidoRepository;
import modelo.entidades.Estado;
import modelo.entidades.Pedido;
import modelo.entidades.Producto;

public class PedidoService {

    private static final int MONTO_PARA_DESCUENTO = 1000;
    private static final int MONTO_POSIBLE_FRAUDE = 5000;
    private static final double DESCUENTO = 0.10;
    private static final double IVA = 0.16;

    private final PedidoRepository repositorio;
    public PedidoService(PedidoRepository repositorio){
        this.repositorio = repositorio;
    }

    public int registrarPedido(Pedido pedido) throws IllegalArgumentException{
        /*
        procesa el pedido lanza excepción
        si el pedido no se procesa correctamente
        se maneja en la UI
         */
        procesarPedido(pedido);
        return guardar(pedido);
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

    private void procesarPedido(Pedido pedido){
        // Procesar pedido
        validarDatos(pedido);
        comprobarDisponibilidad(pedido);
        calcularSubtotal(pedido);
        verificarFraude(pedido);
        aplicarDescuento(pedido);
        calcularImpuestos(pedido);
        confirmarPedido(pedido);

    }

    private void aplicarDescuento(Pedido pedido){
        double subtotal = pedido.getSubtotal();
        pedido.setDescuento(subtotal >= MONTO_PARA_DESCUENTO ? subtotal * DESCUENTO : 0);
    }

    private void calcularImpuestos(Pedido pedido){
        // Obtenemos los valores actuales del pedido
        double subtotal = pedido.getSubtotal();
        double descuento = pedido.getDescuento();


        double baseParaImpuesto = subtotal - descuento;


        double impuestos = baseParaImpuesto * IVA;
        pedido.setImpuestos(impuestos);


        double total = baseParaImpuesto + impuestos;
        pedido.setTotal(total);

    }

    private void calcularSubtotal(Pedido pedido){
        double subtotal = 0;

        for (Producto producto : pedido.getListaDeProductos()){
            subtotal += producto.getPrecioDeProducto() * producto.getCantidadSolicitada();
        }

        pedido.setSubtotal(subtotal);

    }

    private void comprobarDisponibilidad(Pedido pedido){
        for(Producto producto : pedido.getListaDeProductos()){
            if(producto.getCantidadSolicitada() > producto.getExistencia()){
                throw new IllegalArgumentException("No hay suficiente existencia de " + producto.getNombreDeProducto());
            }
        }
    }

    private void confirmarPedido(Pedido pedido){
        // Actualizamos el estado del pedido a PROCESADO para confirmarlo
        pedido.setEstado(Estado.PROCESADO);

    }

    private void validarDatos(Pedido pedido){
        if(pedido.getCliente() == null || pedido.getCliente().isEmpty()){
            throw new IllegalArgumentException("El pedido no tiene cliente");
        }
        if(pedido.getListaDeProductos() == null || pedido.getListaDeProductos().isEmpty()){
            throw new IllegalArgumentException("El pedido no tiene productos");
        }
    }

    private void verificarFraude(Pedido pedido){
        // Regla del reto: Si el subtotal supera los $5,000, se marca para revisión.
        if (pedido.getSubtotal() > MONTO_POSIBLE_FRAUDE) {
            pedido.setPosibleFraude(true);
        }

    }
}
