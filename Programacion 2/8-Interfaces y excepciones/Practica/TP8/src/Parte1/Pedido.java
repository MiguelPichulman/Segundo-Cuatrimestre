
package Parte1;

/**
 *
 * @author Miguel
 */
import java.util.List;

public class Pedido implements Pagable {
    private List<Producto> productos;
    private String estado;
    private Cliente cliente;

    public Pedido(List<Producto> productos, Cliente cliente) {
        this.productos = productos;
        this.cliente = cliente;
    }

    @Override
    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularTotal();
        }
        return total;
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        if (cliente != null) {
            cliente.notificar("El estado del pedido ha cambiado a: " + nuevoEstado);
        }
    }
}
