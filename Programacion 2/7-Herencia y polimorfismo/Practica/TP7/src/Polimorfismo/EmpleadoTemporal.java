
package Polimorfismo;

public class EmpleadoTemporal extends Empleado {
    private int horasTrabajadas;
    private double valorHora;

    public EmpleadoTemporal(String nombre, int horas, double valorHora) {
        super(nombre);
        this.horasTrabajadas = horas;
        this.valorHora = valorHora;
    }

    @Override
    public double calcularSueldo() {
        return horasTrabajadas * valorHora;
    }
}

