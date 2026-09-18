package pruebas;

import entidades.Pedido;
import entidades.Producto;
import filtros.AplicarDescuento;
import filtros.CalcularSubtotal;
import filtros.ComprobarDisponibilidad;
import filtros.ValidarDatos;
import java.util.List;
import tuberias.Tuberia;

public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido("Erick", 
        List.of(new Producto("lapiz", 45.5, 3,10), 
        new Producto("Cepillo", 20, 1, 14)));


        Tuberia tuberia = new Tuberia(List.of(new ComprobarDisponibilidad(), new CalcularSubtotal(), new AplicarDescuento(), new ValidarDatos()));
        tuberia.ejecutar(pedido);
        
        return;
    }
}
