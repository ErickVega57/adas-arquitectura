package servicios;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import comunicacion.MensajeBroker;

/**
 * Actúa como un servidor proxy para un servicio específico.
 * Se encarga de registrar el servicio en el Broker y de gestionar 
 * el ciclo de vida de las conexiones de red entrantes, delegando 
 * la ejecución de la lógica de negocio a la implementación del servicio.
 */
public class ServidorProxy {

    private String nombreServicio;
    private String hostBroker;
    private int puertoBroker;
    private String hostServicio;
    private int puertoServicio;
    private Servicio servicio;

    /**
     * Construye un nuevo ServidorProxy para un servicio.
     *
     * @param nombreServicio El nombre identificador del servicio (ej. "Inventario").
     * @param hostBroker     La dirección host donde se encuentra ejecutándose el Broker.
     * @param puertoBroker   El puerto de red en el que escucha el Broker.
     * @param hostServicio   La dirección host donde se ejecutará este servicio.
     * @param puertoServicio El puerto de red en el que este servicio escuchará peticiones.
     * @param servicio       La instancia con la lógica de negocio que implementa la interfaz {@link Servicio}.
     */
    public ServidorProxy(
        String nombreServicio,
        String hostBroker,
        int puertoBroker,
        String hostServicio,
        int puertoServicio,
        Servicio servicio
    ) {
        this.nombreServicio = nombreServicio;
        this.hostBroker = hostBroker;
        this.puertoBroker = puertoBroker;
        this.hostServicio = hostServicio;
        this.puertoServicio = puertoServicio;
        this.servicio = servicio;
    }

    /**
     * Inicia el servidor proxy.
     * Primero intenta registrar el servicio en el Broker. Si es exitoso, 
     * abre un {@link ServerSocket} y comienza a escuchar peticiones en un bucle infinito.
     * Cada petición recibida se pasa al método ejecutar() del servicio.
     */
    public void iniciar() {

        registrarEnBroker();

        try (ServerSocket servidor = new ServerSocket(puertoServicio)) {

            System.out.println(nombreServicio + " escuchando en puerto " + puertoServicio);

            while (true) {
                try (
                    Socket socket = servidor.accept();
                    ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream())
                ) {
                    MensajeBroker solicitud = (MensajeBroker) entrada.readObject();
                    
                    // Delega la operación a la lógica concreta del servicio
                    MensajeBroker respuesta = servicio.ejecutar(solicitud);

                    salida.writeObject(respuesta);
                    salida.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Envía un mensaje de tipo "REGISTRAR" al Broker para informar 
     * sobre la disponibilidad de este servicio y su dirección (host y puerto).
     * 
     * @throws RuntimeException Si la conexión con el Broker falla o si el Broker rechaza el registro.
     */
    private void registrarEnBroker() {

        MensajeBroker registro = new MensajeBroker("REGISTRAR");
        registro.setServicio(nombreServicio);
        registro.setHost(hostServicio);
        registro.setPuerto(puertoServicio);

        try (
            Socket socket = new Socket(hostBroker, puertoBroker);
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())
        ) {
            salida.writeObject(registro);
            salida.flush();

            MensajeBroker respuesta = (MensajeBroker) entrada.readObject();

            if (!respuesta.isExito()) {
                throw new RuntimeException(respuesta.getDatos());
            }

        } catch (Exception e) {
            throw new RuntimeException(
                "No se pudo registrar " + nombreServicio + " en el Broker: " + e.getMessage()
            );
        }
    }
}
