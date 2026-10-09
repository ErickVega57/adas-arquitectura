package modelo;

import observer.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoModelo {

    private Map<Integer, Pedido> pedidos = new HashMap<>();
    private int siguienteId = 1;

    private List<Observer> observers = new ArrayList<>();
    private Object data = null;

    public Object getData() {
        return data;
    }

    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    private void notificar() {
        for(Observer observer : observers) {
            observer.update();
        }
    }

    public Pedido registrarPedido(Pedido pedido) {
        // Validaciones
        if (pedido.getCliente() == null || pedido.getCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El cliente no puede estar vacío.");
        }
        if (pedido.getListaDeProductos() == null || pedido.getListaDeProductos().isEmpty()) {
            throw new IllegalArgumentException("Debe existir al menos un producto.");
        }

        double subtotalCalculado = 0.0;
        for (Producto p : pedido.getListaDeProductos()) {
            if (p.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor que cero para: " + p.getNombre());
            }
            if (p.getCantidad() > p.getExistencia()) {
                throw new IllegalArgumentException("La cantidad supera la existencia para: " + p.getNombre());
            }
            subtotalCalculado += (p.getPrecio() * p.getCantidad());
        }

        
        pedido.setSubtotal(subtotalCalculado);

        double descuentoCalculado = (subtotalCalculado >= 1000.0) ? (subtotalCalculado * 0.10) : 0.0;
        pedido.setDescuento(descuentoCalculado);

        double baseImponible = subtotalCalculado - descuentoCalculado;
        double impuestosCalculados = baseImponible * 0.16;
        pedido.setImpuestos(impuestosCalculados);

        pedido.setTotal(baseImponible + impuestosCalculados);
        pedido.setEstado(Estado.PROCESADO);

        // Almacenamiento
        pedido.setId(siguienteId);
        pedidos.put(siguienteId, pedido);
        siguienteId++;

        // Notificar a la Vista
        this.data = pedido;
        notificar();

        return pedido;
    }

    public Pedido consultarPedido(int id) {
        Pedido pedidoConsultado = pedidos.get(id);
        
        if (pedidoConsultado == null) {
            // Lanza el error para que el Controlador lo atrape e imprima "Pedido no encontrado"
            throw new IllegalArgumentException("Pedido no encontrado");
        }
        
        this.data = pedidoConsultado;
        notificar();
        return pedidoConsultado;
    }
}
