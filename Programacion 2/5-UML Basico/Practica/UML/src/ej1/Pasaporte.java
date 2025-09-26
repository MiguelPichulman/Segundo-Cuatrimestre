package ej1;

import java.util.Date;

class Pasaporte {
    private String numero;
    private Date fechaEmision;
    private Foto foto;  // Composición: Pasaporte contiene Foto
    private Titular titular;  // Asociación bidireccional

    public Pasaporte(String numero, Date fechaEmision, String imagen, String formato, Titular titular) {
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.foto = new Foto(imagen, formato); // Foto creada dentro de Pasaporte
        this.titular = titular;
        if (titular != null) {
            titular.setPasaporte(this);  // Establecer la asociación bidireccional
        }
    }

    public String getNumero() {
        return numero;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public Foto getFoto() {
        return foto;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
        if (titular != null) {
            titular.setPasaporte(this);
        }
    }
}