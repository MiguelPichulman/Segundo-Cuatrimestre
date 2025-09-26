package ej2;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Bateria bateria = new Bateria("ModeloX", 4000);

        // Crear un usuario
        Usuario usuario = new Usuario("Ana García", "87654321");

        // Crear un celular con batería y usuario
        Celular celular = new Celular("123456789012345", "Samsung", "Galaxy S21", bateria, usuario);

        // Mostrar información del celular y usuario
        System.out.println("Celular IMEI: " + celular.getImei());
        System.out.println("Marca: " + celular.getMarca());
        System.out.println("Modelo: " + celular.getModelo());
        System.out.println("Batería Modelo: " + celular.getBateria().getModelo());
        System.out.println("Capacidad Batería: " + celular.getBateria().getCapacidad() + " mAh");
        System.out.println("Usuario: " + celular.getUsuario().getNombre());
        System.out.println("DNI Usuario: " + celular.getUsuario().getDni());
    }
}
