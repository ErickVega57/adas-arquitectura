# Implementación de Tuberías y filtros en Java

**Objetivo**. Implementar un prototipo del Sistema de procesamiento de pedidos utilizando el estilo arquitectónico **Tuberías y filtros**, de manera que cada transformación del pedido sea independiente y pueda incorporarse al flujo sin modificar las demás.

## Requisitos funcionales

El sistema deberá procesar un pedido mediante las siguientes etapas:

1. Validar los datos del pedido.
2. Comprobar la disponibilidad de los productos.
3. Calcular el subtotal.
4. Aplicar un descuento cuando corresponda.
5. Calcular los impuestos.
6. Confirmar el pedido.

El pedido deberá contener, como mínimo:

- cliente;
- lista de productos;
- precio de cada producto;
- cantidad solicitada;
- existencia disponible;
- subtotal;
- descuento;
- impuestos;
- total;
- estado.

## Estructura arquitectónica requerida
Todos los filtros deberán implementar una interfaz común:

``` java
public interface Filtro {
    Pedido procesar(Pedido pedido);
}
```

Deberán crearse, al menos, las siguientes implementaciones:

*ValidarDatos*<br>
*ComprobarDisponibilidad*<br>
*CalcularSubtotal*<br>
*AplicarDescuento*<br>
*CalcularImpuestos*<br>
*ConfirmarPedido*

Cada clase deberá tener una responsabilidad claramente definida.

### Construcción de la tubería

El programa deberá almacenar los filtros en una colección que determine el orden de ejecución.

Una posible estructura es:
``` java
List<Filtro> pipeline = List.of(
    new ValidarDatos(),
    new ComprobarDisponibilidad(),
    new CalcularSubtotal(),
    new AplicarDescuento(),
    new CalcularImpuestos(),
    new ConfirmarPedido()
);
```
El pedido deberá recorrer la tubería:

``` java
for (Filtro filtro : pipeline) {
    pedido = filtro.procesar(pedido);
}
```
Restricciones arquitectónicas

La implementación deberá cumplir las siguientes condiciones:

1. Un filtro no debe invocar directamente a otro filtro.
2. Un filtro no debe conocer cuál se ejecutó antes ni cuál se ejecutará después.
3. Todos los filtros deben recibir y devolver un objeto compatible.
4. La secuencia de procesamiento debe definirse fuera de los filtros.
5. Agregar un nuevo filtro no deberá requerir modificar los filtros existentes.

**Reto obligatorio**

Agregar un filtro: ***VerificarFraude***

Regla sencilla:

Si el subtotal supera $5,000, el pedido deberá marcarse para revisión.

El filtro deberá incorporarse entre:

CalcularSubtotal
↓
VerificarFraude
↓
AplicarDescuento

No se permite modificar las clases de los filtros existentes para incorporar esta funcionalidad.

Pruebas mínimas

El programa deberá demostrar al menos estos casos:

- pedido válido;
- pedido sin productos;
- producto con cantidad superior a la existencia;
- pedido con descuento;
- pedido sin descuento;
- pedido marcado para revisión de fraude.
- Imprimir el estado del pedido después de cada filtro para poder observar el flujo.

## Entregables

- proyecto Java completo;
- código fuente;
- diagrama UML de la solución;
- captura o evidencia de ejecución;
