
package Parte1;

import java.util.*;

public class Ecomerce {
    public static void main(String[] args) {
        
        Producto prod1 = new Producto("Notebook", 1000.0);
        Producto prod2 = new Producto("Mouse", 50.0);

        Cliente cli = new Cliente("Ana", "ana@email.com");

        List<Producto> productos = Arrays.asList(prod1, prod2);
        Pedido pedido = new Pedido(productos, cli);

        double total = pedido.calcularTotal();
        System.out.println("Total del pedido: $" + total);
      
        pedido.cambiarEstado("Enviado");

        PagoConDescuento pago = new TarjetaCredito();
        double totalConDescuento = pago.aplicarDescuento(total);
        pago.procesarPago(totalConDescuento);

        Pago pagoPayPal = new PayPal();
        pagoPayPal.procesarPago(total);
    }
}

