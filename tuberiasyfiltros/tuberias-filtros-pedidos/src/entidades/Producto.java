package entidades;

public class Producto {

    private final String nombreDeProducto;
    private final double precioDeProducto;
    private final int cantidadSolicitada;
    private final int existencia;

    public Producto(String nombreDeProducto, double precioDeProducto, int cantidadSolicitada, int existencia){
        this.nombreDeProducto = nombreDeProducto;
        this.precioDeProducto = precioDeProducto;
        this.cantidadSolicitada = cantidadSolicitada;
        this.existencia = existencia;
    }

    public String getNombreDeProducto(){
        return nombreDeProducto;
    }

    public double getPrecioDeProducto() {
        return precioDeProducto;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public int getExistencia() {
        return existencia;
    }

    @Override
    public String toString() {
        return String.format(
            "\nNombre: %s, " +
            "Precio: %.2f, " +
            "Cantidad Solicitada: %d, " +
            "Existencia: %d", 
            nombreDeProducto, precioDeProducto, cantidadSolicitada, existencia);
    }

    
}
