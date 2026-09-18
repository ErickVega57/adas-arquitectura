package filtros;

import entidades.Pedido;

public class CalcularImpuestos implements Filtro {

    @Override
    public Pedido procesar(Pedido pedido) {
        // Obtenemos los valores actuales del pedido
        double subtotal = pedido.getSubtotal();
        double descuento = pedido.getDescuento();
        
      
        double baseParaImpuesto = subtotal - descuento;
        
       
        double impuestos = baseParaImpuesto * 0.16;
        pedido.setImpuestos(impuestos);
        
  
        double total = baseParaImpuesto + impuestos;
        pedido.setTotal(total);
        
        return pedido;
    }
}
