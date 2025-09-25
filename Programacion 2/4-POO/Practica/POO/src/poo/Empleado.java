package poo;

/**
 *
 * @author migue
 */
public class Empleado {
    
    private int id;
    private static int generadorId = 0;
    private String nombre;
    private String puesto;
    private double salario;
    private static int totalEmpleados = 0;

    public Empleado(int id, String nombre, String puesto, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        totalEmpleados++;
    }

    public Empleado(String nombre, String puesto) {
        this.id = ++generadorId;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = 350000;
        totalEmpleados++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public static int getTotalEmpleados() {
        return totalEmpleados;
    }

    public static void setTotalEmpleados(int totalEmpleados) {
        Empleado.totalEmpleados = totalEmpleados;
    }
    
    public void actualizarSalario(int porcentaje){
        this.salario = this.salario * (1+porcentaje/100);
    }
    
    public void actualizarSalario(double monto){
        this.salario = this.salario + monto;
    }

    @Override
    public String toString() {
        return "\nEmpleado{\n" + "ID: " + id + "\n Nombre: " + nombre + "\n Puesto:" + puesto + "\n Salario:" + salario + '}';
    }    
}
