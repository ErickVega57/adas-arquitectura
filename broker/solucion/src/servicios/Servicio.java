package servicios;

import comunicacion.MensajeBroker;

/**
 * Interfaz base que deben implementar todos los servicios gestionados por el sistema.
 */
public interface Servicio {
    /**
     * Ejecuta una operación solicitada.
     *
     * @param solicitud El mensaje de solicitud que contiene los datos y la operación.
     * @return El mensaje de respuesta con el resultado de la ejecución.
     */
    MensajeBroker ejecutar(MensajeBroker solicitud);
}