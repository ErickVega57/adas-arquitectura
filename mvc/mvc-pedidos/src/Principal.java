import controlador.PedidoControlador;
import modelo.PedidoModelo;
import vista.PedidoVista;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PedidoVista vista = new PedidoVista(scanner);
        PedidoModelo modelo = new PedidoModelo();
        PedidoControlador controlador = new PedidoControlador();

        controlador.iniciar();
    }
}
