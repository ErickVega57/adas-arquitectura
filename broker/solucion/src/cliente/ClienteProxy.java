package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import comunicacion.MensajeBroker;

/**
 * Clase que encapsula la complejidad de red para los clientes, 
 * encargándose de abrir el socket, enviar la solicitud al Broker y recuperar la respuesta.
 */
public class ClienteProxy {

    private String hostBroker;
    private int puertoBroker;

    /**
     * Construye un proxy para el cliente.
     *
     * @param hostBroker   El host donde se encuentra el Broker.
     * @param puertoBroker El puerto donde el Broker escucha.
     */
    public ClienteProxy(String hostBroker, int puertoBroker) {
        this.hostBroker = hostBroker;
        this.puertoBroker = puertoBroker;
    }

    /**
     * Envía una solicitud de invocación de servicio al Broker.
     *
     * @param servicio  Nombre del servicio a invocar (ej. "Inventario").
     * @param operacion Nombre de la operación a ejecutar (ej. "VERIFICAR").
     * @param datos     Los argumentos en formato de cadena.
     * @return El mensaje de respuesta devuelto por el servicio o un mensaje de error si falla la conexión.
     */
    public MensajeBroker invocar(String servicio, String operacion, String datos) {
        MensajeBroker solicitud = new MensajeBroker("INVOCAR");
        solicitud.setServicio(servicio);
        solicitud.setOperacion(operacion);
        solicitud.setDatos(datos);

        try (
            Socket socket = new Socket(hostBroker, puertoBroker);
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())
        ) {
            salida.writeObject(solicitud);
            salida.flush();
            return (MensajeBroker) entrada.readObject();
        } catch (Exception e) {
            MensajeBroker respuesta = new MensajeBroker("ERROR");
            respuesta.setExito(false);
            respuesta.setDatos("Error al contactar al Broker: " + e.getMessage());
            return respuesta;
        }
    }
}