package servicios;

import comunicacion.MensajeBroker;

/**
 * Implementación de lógica de negocio para el servicio de inventario.
 */
public class ServicioInventario implements Servicio {

    @Override
    public MensajeBroker ejecutar(MensajeBroker solicitud) {
        MensajeBroker respuesta = new MensajeBroker("RESPUESTA");

        if ("VERIFICAR".equals(solicitud.getOperacion())) {
            // Formato de datos: producto,cantidad
            String[] partes = solicitud.getDatos().split(",");
            String producto = partes[0];
            int cantidad = Integer.parseInt(partes[1]);

            int existencias = "P001".equals(producto) ? 10 : 0;
            boolean disponible = existencias >= cantidad;

            respuesta.setExito(true);
            respuesta.setDatos(
                "producto=" + producto
                + ",disponible=" + disponible
                + ",existencias=" + existencias
            );
        } else {
            respuesta.setExito(false);
            respuesta.setDatos("Operación no soportada");
        }
        return respuesta;
    }
}