# Capa de logica de negocio
Responsable de:

- validar pedidos.
- calcular subtotal.
- aplicar descuentos.
- calcular impuestos.
- determinar el estado del pedido.
- coordinar el registro y consulta de pedidos.

Una posible clase:
```java
public class PedidoService {

    private PedidoRepository repositorio;

    public PedidoService(PedidoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Pedido registrar(Pedido pedido) {
    // reglas de negocio
        return pedido;
    }

}
```
Esta capa **no deberá conocer cómo se almacenan físicamente los pedidos**.

La lógica de negocio **deberá depender de PedidoRepository**, no directamente de PedidoRepositoryMemoria.

## Reglas de negocio

Validación:
- El cliente no puede estar vacío.
- El pedido debe contener al menos un producto.
- La cantidad solicitada debe ser mayor que cero.
- No puede solicitarse una cantidad superior a la existencia disponible.


Podemos aprovechar lo hecho en tuberías y filtros:

Una posible clase:
```java
public class PedidoService {

    private PedidoRepository repositorio;
    private Tuberia tuberia;

    public PedidoService(PedidoRepository repositorio, Tuberia tuberia) {
        this.repositorio = repositorio;
        this.tuberia = tuberia;
    }

    public Pedido registrar(Pedido pedido) {
        // ejemplo
        tuberia.recorrer(pedido);
        // guardar
        return pedido;
    }
```
