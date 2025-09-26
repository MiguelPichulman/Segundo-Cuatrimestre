/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej10;

/**
 *
 * @author migue
 */
public class CuentaBancaria {
    private String cbu;
    private double saldo;
    private ClaveSeguridad claveSeguridad;  // Composicion
    private Titular titular;  // Asociacion bidireccional

    public CuentaBancaria(String cbu, double saldo, String codigoClave, String fechaModificacion, Titular titular) {
        this.cbu = cbu;
        this.saldo = saldo;
        this.claveSeguridad = new ClaveSeguridad(codigoClave, fechaModificacion);  // Composicion crea ClaveSeguridad
        this.titular = titular;
        if (titular != null) {
            titular.setCuenta(this);
        }
    }

    public String getCbu() {
        return cbu;
    }

    public double getSaldo() {
        return saldo;
    }

    public ClaveSeguridad getClaveSeguridad() {
        return claveSeguridad;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
        if (titular != null) {
            titular.setCuenta(this);
        }
    }
}
