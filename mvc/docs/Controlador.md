### Detalle del Flujo de Ejecución:
1. **Entrada de Datos (Vista ◄── Usuario):** La Vista muestra el menú principal y devuelve un entero con la opción seleccionada.
2. **Evaluación de la Opción (Controlador):** El Controlador evalúa la opción dentro del ciclo `while`. Si requiere datos adicionales (como capturar un pedido o buscar un ID), invoca los métodos especializados de la Vista.
3. **Delegación e Invocar Lógica (Controlador ──► Modelo):** El Controlador le pasa los datos recopilados al Modelo.
4. **Manejo de Respuestas / Errores:**
   - **Caso Exitoso:** El Modelo procesa los datos y ejecuta `notificarObservadores()`. La Vista recibe el `update()` y muestra los resultados actualizados.
   - **Caso de Error:** El Modelo **lanza una excepción** sin ejecutar `notify()`. El Controlador captura la excepción mediante un bloque `try-catch` y llama a `vista.mostrarError()` para notificar al usuario.

---

## 📋 Responsabilidades del Controlador

### 1. Manejo del Ciclo Principal (`while`)
El Controlador es el dueño del flujo de la aplicación. Mantiene un bucle activo hasta que el usuario elija la opción de salir.

### 2. Captura y Paso de Mensajes
Obtiene las entradas leídas por la Vista (objetos o datos simples como un ID) y los envía a los métodos correspondientes del Modelo.

### 3. Captura de Excepciones (`try-catch`)
Protege la ejecución del programa delegando los errores del Modelo hacia la Vista.

### 4. Implementación del Método `update()` en Consola
Dado que es una aplicación por consola:
- **La Vista** implementa su `update()` para reimprimir o refrescar el estado en pantalla.
- **El Controlador** implementa `update()` para cumplir con la interfaz `Observador`. Se deja vacío (o con logs de auditoría) para evitar que salidas impresas interrumpan el formato del menú por consola.

---

## ⚙️ Estructura de Código de Ejemplo (Pseudocódigo / Java-like)

### 1. El Controlador
```java
public class ControladorPedido implementa Observador {
    private ModeloPedido modelo;
    private VistaPedido vista;

    public ControladorPedido(ModeloPedido modelo, VistaPedido vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Registrar observadores en el Modelo
        this.modelo.agregarObservador(this);
        this.modelo.agregarObservador(this.vista);
    }

    public void iniciar() {
        int opcion = -1;
        final int OPCION_SALIR = 0;

        while (opcion != OPCION_SALIR) {
            // 1. Mostrar menú y leer opción desde la Vista
            opcion = this.vista.mostrarMenu();

            // 2. Procesar la opción elegida
            try {
                switch (opcion) {
                    case 1: // Capturar Pedido
                        Pedido nuevoPedido = this.vista.capturarPedido();
                        this.modelo.registrarPedido(nuevoPedido); 
                        // Nota: Si agregarPedido tiene éxito, el modelo ejecuta notify()
                        break;

                    case 2: // Buscar por ID
                        int id = this.vista.buscarPorId();
                        Pedido pedido = this.modelo.obtenerPedidoPorId(id);
                        break;

                    case OPCION_SALIR:
                        this.vista.mostrarMensaje("Saliendo del sistema...");
                        break;

                    default:
                        this.vista.mostrarError("Opción inválida. Intente de nuevo.");
                }
            } catch (Exception e) {
                // Captura fallos del modelo (ej. ID no encontrado, datos inválidos)
                this.vista.mostrarError(e.getMessage());
            }
        }
    }

    @Override
    public void update() {
        // Implementación del método por contrato de la interfaz Observador.
        // En consola, se deja vacío para no alterar el formato visual del menú,
        // ya que la Vista se encarga de mostrar la información actualizada.

        // EJEMPLO UN LOG QUE DIGA "EL MODELO SE A ACTUALIZADO CORRECTAMENTE"
    }
}