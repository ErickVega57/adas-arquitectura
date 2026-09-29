package pruebas;

import datos.PedidoRepository;
import datos.PedidoRepositoryArchivo;
import datos.PedidoRepositoryMemoria;
import logicanegocio.servicio.PedidoService;
import presentacion.MenuPrincipal;
import presentacion.PedidoUI;

import java.util.Scanner;

public class Pruebas {

    public static void pruebaMemoria(){
        PedidoRepository repositorioMemoria = new PedidoRepositoryMemoria();

        PedidoService servicio = new PedidoService(repositorioMemoria);

        Scanner sc = new Scanner(System.in);
        PedidoUI pedidoUI = new PedidoUI(servicio,sc);

        MenuPrincipal menuPrincipal = new MenuPrincipal(sc, pedidoUI);

        menuPrincipal.ejecutar();
    }

    public static void pruebaArchivo(){
        PedidoRepository repositorioArchivo = new PedidoRepositoryArchivo();

        PedidoService servicio = new PedidoService(repositorioArchivo);

        Scanner sc = new Scanner(System.in);
        PedidoUI pedidoUI = new PedidoUI(servicio,sc);

        MenuPrincipal menuPrincipal = new MenuPrincipal(sc, pedidoUI);

        menuPrincipal.ejecutar();
    }

    public static void main(String[] args) {
        System.out.println("===== MEMORIA =====");
        pruebaMemoria();
        System.out.println("===== ARCHIVO =====");
        pruebaArchivo();
    }
}
