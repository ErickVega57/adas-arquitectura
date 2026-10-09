package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import observer.Observer;
import vista.PedidoVista;

public class PedidoControlador implements Observer {
    private final PedidoModelo modelo;
    private final PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista){
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        int opcion = -1;
        final int OPCION_SALIR = 0;

        while (opcion != OPCION_SALIR) {
            opcion = this.vista.mostrarMenu();

            try {
                switch (opcion) {
                    case 1: // Capturar Pedido
                        capturarPedido();
                        break;

                    case 2: // Buscar por ID
                        buscarPorId();
                        break;

                    case OPCION_SALIR:
                        salir();
                        break;

                    default:
                        opcionInvalida();
                }
            } catch (Exception e) {
                // Captura fallos del modelo (ej. ID no encontrado, datos inválidos)
                this.vista.mostrarError(e.getMessage());
            }
        }
    }

    private void capturarPedido(){
        Pedido nuevoPedido = this.vista.capturarPedido();
        this.modelo.registrarPedido(nuevoPedido);
    }

    private void buscarPorId(){
        int id = this.vista.buscarPorId();
        
        Pedido pedido = this.modelo.consultarPedido(id);
    }

    private void salir(){
        this.vista.mostrarMensaje("Saliendo del sistema...");
    }

    private void opcionInvalida(){
        this.vista.mostrarError("Opción inválida. Intente de nuevo.");
    }

    @Override
    public void update() {
    }
}
