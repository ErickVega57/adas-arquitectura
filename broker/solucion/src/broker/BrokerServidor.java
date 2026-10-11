package broker;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import comunicacion.MensajeBroker;

/**
 * Servidor principal que actúa como intermediario (Broker).
 * Se encarga de registrar los servicios disponibles y enrutar las peticiones
 * de invocación desde los clientes hacia los servicios correspondientes.
 */
public class BrokerServidor {

    private int puerto;
    /** Mapa concurrente que asocia el nombre de un servicio con su dirección de red. */
    private Map<String, DireccionServicio> servicios = new ConcurrentHashMap<>();

    /**
     * Construye una instancia del BrokerServidor.
     *
     * @param puerto El puerto en el que el servidor escuchará conexiones entrantes.
     */
    public BrokerServidor(int puerto) {
        this.puerto = puerto;
    }

    /**
     * Inicia el ciclo de escucha del servidor, aceptando conexiones de forma indefinida.
     * Cada conexión entrante se procesa en un hilo (Thread) independiente.
     */
    public void iniciar() {
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Broker escuchando en puerto " + puerto);
            while (true) {
                Socket socket = servidor.accept();
                new Thread(() -> atender(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Atiende una conexión individual recibida por el socket.
     *
     * @param socket El socket de la conexión entrante.
     */
    private void atender(Socket socket) {
        try (
            Socket s = socket;
            ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream salida = new ObjectOutputStream(s.getOutputStream())
        ) {
            MensajeBroker mensaje = (MensajeBroker) entrada.readObject();
            MensajeBroker respuesta;

            if ("REGISTRAR".equals(mensaje.getTipo())) {
                respuesta = registrar(mensaje);
            } else if ("INVOCAR".equals(mensaje.getTipo())) {
                respuesta = invocar(mensaje);
            } else {
                respuesta = error("Tipo de mensaje no reconocido");
            }

            salida.writeObject(respuesta);
            salida.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Registra un nuevo servicio en el mapa de servicios disponibles.
     *
     * @param mensaje El mensaje con los datos del servicio a registrar.
     * @return Un mensaje de respuesta confirmando el registro exitoso.
     */
    private MensajeBroker registrar(MensajeBroker mensaje) {
        servicios.put(
            mensaje.getServicio(),
            new DireccionServicio(mensaje.getHost(), mensaje.getPuerto())
        );

        System.out.println("Servicio registrado: " + mensaje.getServicio() + " -> " + mensaje.getHost() + ":" + mensaje.getPuerto());

        MensajeBroker respuesta = new MensajeBroker("RESPUESTA");
        respuesta.setExito(true);
        respuesta.setDatos("Servicio registrado");

        return respuesta;
    }

    /**
     * Enruta una solicitud de invocación hacia el servicio correspondiente.
     *
     * @param solicitud El mensaje de solicitud originado por el cliente.
     * @return El mensaje de respuesta proveniente del servicio, o un error si no se contacta.
     */
    private MensajeBroker invocar(MensajeBroker solicitud) {
        DireccionServicio direccion = servicios.get(solicitud.getServicio());

        if (direccion == null) {
            return error("Servicio no registrado: " + solicitud.getServicio());
        }

        try (
            Socket socket = new Socket(direccion.getHost(), direccion.getPuerto());
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())
        ) {
            salida.writeObject(solicitud);
            salida.flush();
            return (MensajeBroker) entrada.readObject();
        } catch (Exception e) {
            return error("No fue posible contactar al servicio: " + e.getMessage());
        }
    }

    /**
     * Genera un mensaje de error genérico.
     *
     * @param texto El texto descriptivo del error.
     * @return Un MensajeBroker configurado como error.
     */
    private MensajeBroker error(String texto) {
        MensajeBroker respuesta = new MensajeBroker("ERROR");
        respuesta.setExito(false);
        respuesta.setDatos(texto);
        return respuesta;
    }

    public static void main(String[] args) {
        new BrokerServidor(6000).iniciar();
    }
}
