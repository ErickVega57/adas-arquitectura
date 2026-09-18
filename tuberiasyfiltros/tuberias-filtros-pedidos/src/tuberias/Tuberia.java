package tuberias;

import entidades.Pedido;
import filtros.Filtro;
import java.util.List;

public class Tuberia {

    private List<Filtro> filtros;

    public Tuberia(List<Filtro> filtros) {
        this.filtros = filtros;
    }

    public Pedido recorrer(Pedido pedido){
        for(Filtro filtro: filtros){
            filtro.procesar(pedido);
            System.out.println(pedido.toString() + "\n");
        }
        return pedido;
    }
    
}
