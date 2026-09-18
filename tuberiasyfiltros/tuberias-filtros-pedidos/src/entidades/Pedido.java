package entidades;

import java.util.List;

public class Pedido {
    
    private final String cliente;
    List<Producto> listaDeProductos;
    double subtotal;
    boolean posibleFraude;
    double descuento;
    double impuestos;
    double total;
    Estado estado;

    public Pedido(String cliente, List<Producto> listaDeProductos) {
        this.cliente = cliente;
        this.listaDeProductos = listaDeProductos;
        this.subtotal = 0.0;
        this.posibleFraude = false;
        this.descuento = 0.0;
        this.impuestos = 0.0;
        this.total = 0.0;
        this.estado = Estado.NO_PROCESADO;
    }

    public String getCliente() {
        return cliente;
    }

    public List<Producto> getListaDeProductos() {
        return listaDeProductos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

     public void setPosibleFraude(boolean posibleFraude) {
        this.posibleFraude = posibleFraude;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format(
            "Cliente: %s \n" +
            "Lista de Productos %s \n" +
            "Subtotal: %.2f \n" +
            "Posible Fraude: %s \n" +
            "Descuento: %.2f \n" +
            "Impuestos: %.2f \n" +
            "Total: %.2f \n" +
            "Estado: %s",
             cliente, listaDeProductos.toString(),subtotal, posibleFraude, descuento, impuestos, total, estado );
    }
    
}
