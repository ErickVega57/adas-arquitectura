package servicios;

/**
 * Clase principal que inicializa e inscribe el servicio de facturación en el Broker.
 */
public class AppFacturacion {
    public static void main(String[] args) {
        ServicioFacturacion facturacion = new ServicioFacturacion();
        ServidorProxy proxy = new ServidorProxy(
            "Facturacion", "localhost", 6000, "localhost", 7002, facturacion
        );
        proxy.iniciar();
    }
}