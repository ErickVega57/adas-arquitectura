package entidades;

public class Producto {

    private final String nombreDeProducto;
    private final double precioDeProducto;
    private final int cantidadSolicitada;

    public Producto(String nombreDeProducto, double precioDeProducto, int cantidadSolicitada){
        this.nombreDeProducto = nombreDeProducto;
        this.precioDeProducto = precioDeProducto;
        this.cantidadSolicitada = cantidadSolicitada;
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
}
