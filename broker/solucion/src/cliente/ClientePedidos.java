package cliente;

import comunicacion.MensajeBroker;

/**
 * Aplicación cliente de prueba que simula un flujo de e-commerce o pedidos.
 * Se comunica con el Broker para verificar el inventario y emitir la facturación.
 */
public class ClientePedidos {

    public static void main(String[] args) {
        ClienteProxy proxy = new ClienteProxy("localhost", 6000);

        MensajeBroker inventario = proxy.invocar("Inventario", "VERIFICAR", "P001,3");
        System.out.println("Respuesta de Inventario:");
        System.out.println(inventario.getDatos());

        MensajeBroker factura = proxy.invocar("Facturacion", "GENERAR_FACTURA", "105,1450.00");
        System.out.println("\nRespuesta de Facturación:");
        System.out.println(factura.getDatos());
    }
}