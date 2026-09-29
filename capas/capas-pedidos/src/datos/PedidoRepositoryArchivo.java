package datos;

import modelo.entidades.Estado;
import modelo.entidades.Pedido;
import modelo.entidades.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositoryArchivo implements PedidoRepository {

    private final String RUTA_ARCHIVO = "pedidos_db.txt";

    @Override
    public int guardar(Pedido pedido) {
        List<Pedido> pedidos = listarTodos();
        
        if (pedido.getId() == 0) {
            int maxId = 0;
            for (Pedido p : pedidos) {
                if (p.getId() > maxId) maxId = p.getId();
            }
            pedido.setId(maxId + 1);
            pedidos.add(pedido);
        } else {
            for (int i = 0; i < pedidos.size(); i++) {
                if (pedidos.get(i).getId() == pedido.getId()) {
                    pedidos.set(i, pedido);
                    break;
                }
            }
        }
        sobrescribirArchivo(pedidos);
        return pedido.getId();
    }

    @Override
    public Pedido buscarPorId(int id) {
        List<Pedido> pedidos = listarTodos();
        for (Pedido p : pedidos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    @Override
    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) return pedidos;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                pedidos.add(deserializarPedido(linea));
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
        return pedidos;
    }

    private void sobrescribirArchivo(List<Pedido> pedidos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            for (Pedido p : pedidos) {
                bw.write(serializarPedido(p));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }

    private String serializarPedido(Pedido p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.getId()).append(";")
          .append(p.getCliente()).append(";")
          .append(p.getSubtotal()).append(";")
          .append(p.getDescuento()).append(";")
          .append(p.getImpuestos()).append(";")
          .append(p.getTotal()).append(";")
          .append(p.getEstado().name()).append(";");
        
        for (Producto prod : p.getListaDeProductos()) {
            sb.append(prod.getNombreDeProducto()).append(",")
              .append(prod.getPrecioDeProducto()).append(",")
              .append(prod.getCantidadSolicitada()).append(",")
              .append(prod.getExistencia()).append("|");
        }
        return sb.toString();
    }

    private Pedido deserializarPedido(String linea) {
        String[] partes = linea.split(";");
        List<Producto> productos = new ArrayList<>();
        
        if (partes.length > 7 && !partes[7].isEmpty()) {
            String[] arrayProductos = partes[7].split("\\|");
            for (String prodStr : arrayProductos) {
                String[] datosProd = prodStr.split(",");
                // Usando el constructor exacto de Producto
                Producto prod = new Producto(datosProd[0], Double.parseDouble(datosProd[1]), 
                                             Integer.parseInt(datosProd[2]), Integer.parseInt(datosProd[3]));
                productos.add(prod);
            }
        }
        
        // Usando el constructor exacto de Pedido
        Pedido p = new Pedido(partes[1], productos);
        p.setId(Integer.parseInt(partes[0]));
        p.setSubtotal(Double.parseDouble(partes[2]));
        p.setDescuento(Double.parseDouble(partes[3]));
        p.setImpuestos(Double.parseDouble(partes[4]));
        p.setTotal(Double.parseDouble(partes[5]));
        p.setEstado(Estado.valueOf(partes[6]));
        
        return p;
    }
}
