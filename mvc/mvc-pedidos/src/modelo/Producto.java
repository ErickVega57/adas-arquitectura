package modelo;

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

    public String getNombre(){
        return nombreDeProducto;
    }

    public double getPrecio() {
        return precioDeProducto;
    }

    public int getCantidad() {
        return cantidadSolicitada;
    }

    public int getExistencia() {
        return existencia;
    }
    
}
