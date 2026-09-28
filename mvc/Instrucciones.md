# Instrucciones
La empresa necesita una aplicación de consola para registrar y consultar pedidos. El sistema debe permitir:

- registrar un pedido;
- consultar un pedido por identificador;
- listar pedidos;
- mostrar errores de validación.

Estructura requerida

La solución deberá contener como mínimo:

```text
Usuario
   ↓
Vista
   ↓ evento
Controlador
   ↓ operación
Modelo
```

El flujo esperado es:

```text
Usuario
   ↓
Vista
   ↓
Controlador
   ↓
Modelo
   ↓
Controlador
   ↓
Vista
   ↓
Usuario
```

### Modelo

El Modelo deberá contener:

- información de pedidos;
- reglas de validación;
- cálculo de subtotal;
- cálculo de descuento;
- cálculo de impuestos;
- cálculo del total;
- registro y consulta de pedidos.

Una posible clase:
```java
public class PedidoModelo {

    private Map<Integer, Pedido> pedidos = new HashMap<>();
    private int siguienteId = 1;

    public Pedido registrarPedido(Pedido pedido) {
        // validar
        // calcular
        // almacenar
        // devolver resultado
        return pedido;
    }

    public Pedido consultarPedido(int id) {
        return pedidos.get(id);
    }
}
```


El Modelo **no deberá imprimir información ni solicitar datos al usuario**.

### Vista

La Vista será inicialmente de consola.

Por ejemplo:
```java
public class PedidoVista {

    public Pedido capturarPedido() {
        // capturar o construir datos
        return pedido;
    }

    public void mostrarResultado(Pedido pedido) {
        // mostrar datos
    }

    public void mostrarError(String mensaje) {
        // mostrar error
    }

    public void mostrarPedido(Pedido pedido) {
        // mostrar consulta
    }
}
```

La Vista:

- Presenta información.
- Captura acciones.
- Muestra mensajes.

No deberá:

- Calcular descuentos.
- Calcular impuestos.
- Almacenar pedidos.
- Aplicar reglas de negocio.

### Controlador

El Controlador coordinará la interacción:
```java
public class PedidoControlador {

    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(
            PedidoModelo modelo,
            PedidoVista vista) {

        this.modelo = modelo;
        this.vista = vista;
    }

    public void registrarPedido() {

        try {
            Pedido pedido =
                vista.capturarPedido();

            Pedido resultado =
                modelo.registrarPedido(pedido);

            vista.mostrarResultado(resultado);

        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}
```

El Controlador **no deberá contener las reglas de cálculo del pedido**.

Modelo de dominio mínimo

Puede mantenerse:

_`Pedido Producto`_

**Producto**
- `nombre`
- `precio`
- `cantidad`
- `existencia`

**Pedido**
- `id`
- `cliente`
- `productos`
- `subtotal`
- `descuento`
- `impuestos`
- `total`
- `estado`

**Reglas de negocio**

Usaría exactamente las mismas que en sistema en [capas](https://extranet.matematicas.uady.mx/enlinea/mod/resource/view.php?id=49659 "Capas") para comparar arquitecturas y no cambie de problema:

- El cliente no puede estar vacío.
- Debe existir al menos un producto.
- La cantidad debe ser mayor que cero.
- No puede superar la existencia.
- Subtotal = Σ precio × cantidad.
- Descuento del 10 % si subtotal ≥ $1,000.
- Impuesto del 16 % sobre subtotal − descuento.
- Estado final = `PROCESADO`.

**Primera parte:** implementar [MVC](https://extranet.matematicas.uady.mx/enlinea/mod/resource/view.php?id=49660 "MVC")

El proyecto podría organizarse así:

```text
src
│
├── modelo
│   ├── Pedido.java
│   ├── Producto.java
│   └── PedidoModelo.java
│
├── vista
│   └── PedidoVista.java
│
├── controlador
│   └── PedidoControlador.java
│
└── Principal.java
```

En `Principal` se construye el [MVC](https://extranet.matematicas.uady.mx/enlinea/mod/resource/view.php?id=49660 "MVC"):

```java
PedidoModelo modelo = new PedidoModelo();  
PedidoVista vista = new PedidoVista();
 
PedidoControlador controlador = new PedidoControlador(modelo, vista);

controlador.registrarPedido();
```

**Segunda parte:** comprobar la separación

La empresa quiere una segunda forma de visualizar los pedidos.
```java
public class PedidoVistaResumida extends PedidoVista {
     ... 
}
```
Esta Vista deberá mostrar únicamente:

_`Pedido: 15 Total: $3915.00 Estado: PROCESADO`_

La condición será:

> **No se permite modificar `PedidoModelo`.**

Después deberán ejecutar el mismo Modelo con:

_`PedidoVista`_

y:

_`PedidoVistaResumida`_

Esto hace observable la **reutilización del Modelo con distintas representaciones**.

**Tercera parte:** agregar consulta

Deberán incorporar:
```java
public void consultarPedido(int id);
```
en el Controlador.

El flujo esperado será:

```text
Vista
  ↓ id
Controlador
  ↓
Modelo.consultarPedido(id)
  ↓
Controlador
  ↓
Vista.mostrarPedido()
```

Si el pedido no existe:
```java
vista.mostrarError("Pedido no encontrado");
```
Evidencias

1. Registrar pedido válido con descuento.
2. Registrar pedido válido sin descuento.
3. Intentar registrar un pedido inválido.
4. Consultar un pedido existente.
5. Consultar un pedido inexistente.
6. Ejecutar el mismo Modelo con la Vista normal y la Vista resumida.

**Entregables**

- Proyecto Java completo.
- Código fuente.
- Diagrama UML de la arquitectura implementada.
- Evidencias de ejecución de los casos.

Entrega por equipo.

Formato del archivo: Apellido1Apellido2Apellido3Apellido4.ZIP.

Fecha límite de entrega: 9 de octubre.
