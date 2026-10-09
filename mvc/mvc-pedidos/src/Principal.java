import controlador.PedidoControlador;
import modelo.PedidoModelo;
import vista.PedidoVista;
import vista.PedidoVistaResumida;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVistaResumida(modelo);
        PedidoControlador controlador = new PedidoControlador(modelo, vista);

        modelo.agregarObserver(controlador);
        modelo.agregarObserver(vista);

        controlador.iniciar();
    }
}
