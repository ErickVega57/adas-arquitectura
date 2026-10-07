import controlador.PedidoControlador;
import modelo.PedidoModelo;
import vista.PedidoVista;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVista(modelo);
        PedidoControlador controlador = new PedidoControlador(vista, modelo);

        controlador.iniciar();
    }
}
