
package Parte1;

/**
 *
 * @author Miguel
 */

public class TarjetaCredito implements PagoConDescuento {
    @Override
    public void procesarPago(double monto) {
        System.out.println("Pago por tarjeta procesado: $" + monto);
    }

    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.85;
    }
}