package comunicacion;

import java.io.Serializable;

/**
 * Representa el mensaje estructurado utilizado para la comunicación en red 
 * entre los clientes, el Broker y los distintos servicios.
 */
public class MensajeBroker implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tipo;
    private String servicio;
    private String operacion;
    private String datos;
    private String host;
    private int puerto;
    private boolean exito;

    /**
     * Crea un nuevo mensaje para el Broker.
     *
     * @param tipo El tipo de mensaje (ej. "REGISTRAR", "INVOCAR", "RESPUESTA", "ERROR").
     */
    public MensajeBroker(String tipo) {
        this.tipo = tipo;
    }

    /** @return El tipo de mensaje. */
    public String getTipo() { return tipo; }

    /** @return El nombre del servicio objetivo o emisor. */
    public String getServicio() { return servicio; }

    /** @param servicio El nombre del servicio a asignar. */
    public void setServicio(String servicio) { this.servicio = servicio; }

    /** @return La operación que se desea ejecutar en el servicio. */
    public String getOperacion() { return operacion; }

    /** @param operacion La operación a asignar. */
    public void setOperacion(String operacion) { this.operacion = operacion; }

    /** @return Los datos o parámetros asociados a la operación. */
    public String getDatos() { return datos; }

    /** @param datos Los datos a asignar. */
    public void setDatos(String datos) { this.datos = datos; }

    /** @return El host del servicio o remitente. */
    public String getHost() { return host; }

    /** @param host El host a asignar. */
    public void setHost(String host) { this.host = host; }

    /** @return El puerto de red. */
    public int getPuerto() { return puerto; }

    /** @param puerto El puerto a asignar. */
    public void setPuerto(int puerto) { this.puerto = puerto; }

    /** @return true si la operación o solicitud fue exitosa, false en caso contrario. */
    public boolean isExito() { return exito; }

    /** @param exito El estado de éxito de la operación. */
    public void setExito(boolean exito) { this.exito = exito; }
}

