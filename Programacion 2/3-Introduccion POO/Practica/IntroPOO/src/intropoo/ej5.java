package intropoo;

import java.util.Scanner;

public class ej5 {

    public static class NaveEspacial {
        private String nombre;
        private int combustible;
        private int kmRecorridos;
        public static final int MAX_COMBUSTIBLE = 100;
        public static final int CONSUMO_POR_100KM = 10;

        public NaveEspacial(String nombre, int combustible) {
            this.nombre = nombre;
            this.combustible = combustible;
            this.kmRecorridos = 0;
        }

        public void despegar() {
            System.out.println("La nave " +this.nombre+ " esta despegando.");
            System.out.println("La nave consume 10 unidades de combustible cada 100 km");
            System.out.println("Combustible inicial: " +this.combustible);
            System.out.println("Maximo capacidad de combustible: " +MAX_COMBUSTIBLE);
        }

        public boolean avanzar(int distancia) {
            int combustibleNecesario = (distancia * NaveEspacial.CONSUMO_POR_100KM) / 100;

            if (combustibleNecesario > this.combustible) {
                System.out.println("No hay suficiente combustible para avanzar " +distancia+ " km.");
                return false;
            } else {
                this.combustible = this.combustible - combustibleNecesario;
                this.kmRecorridos = this.kmRecorridos + distancia;
                System.out.println("La nave avanzo " +distancia+ " km.");
                System.out.println("Combustible restante: " +this.combustible+ " unidades.");
                return true;
            }
        }

        public void recargarCombustible(int cantidad) {
            if (this.combustible + cantidad > NaveEspacial.MAX_COMBUSTIBLE) {
                System.out.println("No se puede recargar esa cantidad. El combustible maximo es " +NaveEspacial.MAX_COMBUSTIBLE);
            } else {
                this.combustible = this.combustible + cantidad;
                System.out.println("Se recargaron " +cantidad+ " unidades de combustible.");
                System.out.println("Combustible actual: " +this.combustible+ " unidades.");
            }
        }

        public int getCombustible() {
            return this.combustible;
        }

        public void mostrarEstado() {
            System.out.println("Estado de la nave " +this.nombre+ " : ");
            System.out.println("Combustible actual: " +this.combustible+ " unidades");
            System.out.println("Kilometros recorridos: " +this.kmRecorridos+ " km");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        NaveEspacial nave = new NaveEspacial("Endurance", 50);
        nave.despegar();

        while (true) {
            int distancia;
            while (true) {
                System.out.print("Ingrese la distancia a avanzar: ");
                distancia = scanner.nextInt();

                int combustibleNecesario = (distancia * NaveEspacial.CONSUMO_POR_100KM) / 100;
                if (combustibleNecesario > nave.getCombustible()) {
                    System.out.println("No hay suficiente combustible para avanzar esa distancia.");
                    System.out.print("Ingrese la cantidad de combustible a recargar: ");
                    int recarga = scanner.nextInt();
                    nave.recargarCombustible(recarga);
                } else {
                    break;
                }
            }

            nave.avanzar(distancia);

            System.out.println("Quiere seguir avanzando? (s/n):");
            String seguir = scanner.next();

            if (!seguir.equalsIgnoreCase("s")) {
                break;
            }
        }

        nave.mostrarEstado();
    }
}
