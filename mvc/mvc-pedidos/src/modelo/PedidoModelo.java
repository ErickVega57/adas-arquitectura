package modelo;

import observer.Observer;

import java.util.List;

public class PedidoModelo {

    // hasmap de pedidos
    private List<Observer> observers;
    private Object data = null;

    public Object getData(){
        return data;
    }

    public void agregarObserver(Observer observer){
        return;
    }

    private void notificar(){
        for(Observer observer: observers){
            observer.update();
        }
    }

    /* EJEMPLO:
    *  public void registrarPedido(Pedido){
    *   // procesar pedido
    *   // si falla lanzar Error
    *   // si no falla hacer :
    *   data = pedidoProcesado;
    *   notificar()
    */

}
