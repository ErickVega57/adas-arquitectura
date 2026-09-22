
# Capa de acceso a datos 

Se deberá definir un contrato para la persistencia:

```java
public interface PedidoRepository {
    int guardar(Pedido pedido);
    Pedido buscarPorId(int id);
}
```
Y una primera implementación:
```java
public class PedidoRepositoryMemoria implements PedidoRepository {
// almacenamiento en memoria
    @Override
    public int guardar(){
        // implementación

    }

    @Override
    public Pedido buscarPorId(int id){
         // implementación
    }

}
```
Una vez funcionando la aplicación, deberán incorporar una segunda implementación del repositorio:

```java
public class PedidoRepositoryArchivo implements PedidoRepository {

     @Override
    public int guardar(){
        // implementación

    }

    @Override
    public Pedido buscarPorId(int id){
         // implementación
    }

}
```

Puede utilizar un archivo de texto sencillo. No es necesario implementar JSON ni utilizar bibliotecas externas.
