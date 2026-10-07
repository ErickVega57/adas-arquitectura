package modelo;

import observer.Observer;

import java.util.List;

public class PedidoModelo {

    List<Observer> observers;

    public Object getData(){
        return null;
    }

    public void agregarObserver(Observer observer){
        return;
    }

    public void notificar(){
        for(Observer observer: observers){
            observer.update();
        }
    }

}
