package tp2;

import java.util.Scanner;

/**
  Modificación de un array de precios y visualización de resultados.  
Crea un programa que: 
a. Declare e inicialice un array con los precios de algunos productos. 
b. Muestre los valores originales de los precios. 
c. Modifique el precio de un producto específico. 
d. Muestre los valores modificados. 
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
public class ej12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        double[] precios = {199, 299.5, 149.75, 399, 89.99};
        
        System.out.println("Precios originales:");
        for (double i : precios) {
            System.out.println("Precios: "+ i);
        }
        System.out.print("Ingrese el nuevo precio para el producto numero 3: ");
        
        precios[2] = sc.nextDouble();
        for(double i : precios){
            System.out.println("Precios modificados: "+ i);
        }
    }
    
}
