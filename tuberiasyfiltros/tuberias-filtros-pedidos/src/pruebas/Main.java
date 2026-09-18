package pruebas;

import entidades.Pedido;
import entidades.Producto;
import filtros.AplicarDescuento;
import filtros.CalcularImpuestos;
import filtros.CalcularSubtotal;
import filtros.ComprobarDisponibilidad;
import filtros.ConfirmarPedido;
import filtros.ValidarDatos;
import filtros.VerificarFraude;
import java.util.List;
import tuberias.Tuberia;

public class Main {
    public static void main(String[] args) {
        Pedido pedidoValido = new Pedido(
            "Raul", 
            List.of(
                new Producto("Lapiz", 45.5, 3, 10), 
                new Producto("Cepillo", 20, 1, 14)
            )
        );
        Pedido pedidoSinProductos = new Pedido(
            "Francisco", 
            List.of()
        );
        Pedido pedidoCantidadMayorExistencia = new Pedido(
            "Pedro", 
            List.of(
                new Producto("Leche", 45.5, 6, 4), 
                new Producto("Cepillo", 20, 1, 14)
            )
        );
        Pedido pedidoConDescuento = new Pedido(
            "Miguel", 
            List.of(
                new Producto("leche", 45.5, 6, 8), 
                new Producto("Computadora", 1500, 2, 17)
            )
        );

        Pedido pedidoFraude = new Pedido(
            "Felipe", 
            List.of(
                new Producto("Tele", 9999.99, 2, 3), 
                new Producto("Computadora", 1500, 2, 17)
            )
        );
       

        Tuberia tuberia = new Tuberia(
            List.of(
                new ValidarDatos(),
                new ComprobarDisponibilidad(),
                new CalcularSubtotal(),
                new VerificarFraude(),
                new AplicarDescuento(),
                new CalcularImpuestos(),
                new ConfirmarPedido()
            )
        );

        tuberia.recorrer(pedidoCantidadMayorExistencia);
        
        return;
    }
}
