
package MetodosAbstractos;

public class main {
    public static void main(String[] args) {
        Figura[] figuras = {
            new Circulo("Círculo", 3),
            new Rectangulo("Rectángulo", 4, 5)
        };

        for (Figura f : figuras) {
            System.out.println(f.nombre + " - Área: " + f.calcularArea());
        }
    }
}

