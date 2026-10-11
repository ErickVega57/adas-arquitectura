package servicios;

import comunicacion.MensajeBroker;

/**
 * Implementación de lógica de negocio para el servicio de facturación.
 */
public class ServicioFacturacion implements Servicio {

    @Override
    public MensajeBroker ejecutar(MensajeBroker solicitud) {
        MensajeBroker respuesta = new MensajeBroker("RESPUESTA");

        if ("GENERAR_FACTURA".equals(solicitud.getOperacion())) {
            // Formato: pedidoId,total
            String[] partes = solicitud.getDatos().split(",");
            String pedidoId = partes[0];
            String total = partes[1];

            respuesta.setExito(true);
            respuesta.setDatos("Factura F-" + pedidoId + " generada por $" + total);
        } else {
            respuesta.setExito(false);
            respuesta.setDatos("Operación no soportada");
        }
        return respuesta;
    }
}