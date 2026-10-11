package servicios;

/**
 * Clase principal que inicializa e inscribe el servicio de inventario en el Broker.
 */
public class AppInventario {
    public static void main(String[] args) {
        ServicioInventario inventario = new ServicioInventario();
        ServidorProxy proxy = new ServidorProxy(
            "Inventario", "localhost", 6000, "localhost", 7001, inventario
        );
        proxy.iniciar();
    }
}