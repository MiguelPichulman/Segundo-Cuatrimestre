package poo;

/**
 *
 * @author migue
 */
public class POO {

    /**
2. Crear una clase de prueba con método main que:
○ Instancie varios objetos usando ambos constructores.
○ Aplique los métodos actualizarSalario() sobre distintos empleados.
○ Imprima la información de cada empleado con toString().
○ Muestre el total de empleados creados con
mostrarTotalEmpleados().
     */
    public static void main(String[] args) {
        Empleado emp1 = new Empleado(100, "Cristian", "Vendedor", 400000);
        Empleado emp2 = new Empleado("Emmanuel", "Analista");
        Empleado emp3 = new Empleado("Joaquin", "Diseñador");
        Empleado emp4 = new Empleado(105, "Miguel", "Gerente", 500000);

        emp1.actualizarSalario(10);    
        emp2.actualizarSalario(5000.0); 
        emp3.actualizarSalario(5);
        emp4.actualizarSalario(15000.0);
        
        System.out.println(emp1);
        System.out.println(emp2);
        System.out.println(emp3);
        System.out.println(emp4);

        System.out.println("");
        System.out.println("Total empleados: " + Empleado.getTotalEmpleados());
    }
    
}
