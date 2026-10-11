package broker;

/**
 * Entidad que almacena los datos de red (host y puerto) de un servicio registrado.
 */
public class DireccionServicio {
    private String host;
    private int puerto;

    /**
     * Construye una nueva dirección de servicio.
     *
     * @param host   La dirección IP o nombre de host del servicio.
     * @param puerto El puerto en el que el servicio está escuchando.
     */
    public DireccionServicio(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    /** @return El host del servicio. */
    public String getHost() { return host; }

    /** @return El puerto del servicio. */
    public int getPuerto() { return puerto; }
}
