
package ComportamientoSobrescrito;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Animal[] animales = {
            new Perro(),
            new Gato(),
            new Vaca()
        };

        for (Animal a : animales) {
            a.hacerSonido();
            a.describirAnimal();
        }
    }

}
