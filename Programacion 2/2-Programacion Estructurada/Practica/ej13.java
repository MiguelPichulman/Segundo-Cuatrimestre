package tp2;

import java.util.Scanner;

/**
Impresión recursiva de arrays antes y después de modificar un elemento. 
Crea un programa que: 
a. Declare e inicialice un array con los precios de algunos productos. 
b. Use una función recursiva para mostrar los precios originales. 
c. Modifique el precio de un producto específico. 
d. Use otra función recursiva para mostrar los valores modificados. 
Salida esperada: 
Precios originales: 
Precio: $199.99 
Precio: $299.5 
Precio: $149.75 
Precio: $399.0 
Precio: $89.99 
Precios modificados: 
Precio: $199.99 
Precio: $299.5 
Precio: $129.99 
Precio: $399.0 
Precio: $89.99
 */
public class ej13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        double[] precios = {199, 299.5, 149.75, 399, 89.99};
        int limite = precios.length;
        System.out.println("Precios originales:");
        for (double i : precios) {
            System.out.println("Precios: "+ i);
        }
        
        System.out.print("Ingrese el nuevo precio para el producto numero 3: ");        
        precios[2] = sc.nextDouble();
        
        mostrarRecursivo(precios, limite-1);
    }
    
    public static void mostrarRecursivo(double [] precios, int limite){
        if (limite<0){
            return;
        }
        mostrarRecursivo(precios, limite -1); 
        System.out.println("Precio: "+ precios[limite]);
    }
}
