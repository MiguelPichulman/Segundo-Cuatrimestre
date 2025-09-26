package ej2;

/**
 *
 * @author migue
 */
class Celular {
    private String imei;
    private String marca;
    private String modelo;
    private Bateria bateria; // Agregación: batería puede existir sola
    private Usuario usuario; // Asociación bidireccional

    public Celular(String imei, String marca, String modelo, Bateria bateria, Usuario usuario) {
        this.imei = imei;
        this.marca = marca;
        this.modelo = modelo;
        this.bateria = bateria;
        this.usuario = usuario;
        if (usuario != null) {
            usuario.setCelular(this); // establecer asociación bidireccional
        }
    }

    public String getImei() {
        return imei;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Bateria getBateria() {
        return bateria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null) {
            usuario.setCelular(this);
        }
    }
}
