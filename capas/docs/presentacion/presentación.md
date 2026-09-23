# Capa de presentacion 

- capturar datos.
- solicitar operaciones a la capa de negocio.
- mostrar resultados y errores.

Puede implementarse inicialmente mediante una aplicación de consola.

posbles clases 

```java
public class MenuPrincipal {

    private Scanner sc;
    public MenuPrincipal(PedidoUI pedido, Scanner sc){
    }

    public void ejecutar(){
        // bucle principal
    }

    private void mostrarMenu(){
    }

    private int leerEntero(){
    }

    private void ejecutarOpcion(int opcion){
        //llamar a pedidoUI
    }

}
```

```java
public class PedidoUI {

    public PedidoUI(PedidoService servicio, Scanner sc){

    }

    public void registrarPedido(){
        //implementacion
    }

    public void buscarPorId(){
        //
    }
    
    private void mostrarError(){
        //implementacion
    }

    private void mostrarPedido(){
        //implementacion
    }

    private String leerLinea(){
        //implementacion
    }


}
```
