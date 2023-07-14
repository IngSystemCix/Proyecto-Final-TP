package pe.edu.utp;
import pe.edu.utp.util.IOSismos;

import java.io.IOException;

/**
 * Esta clase genera objetos substraidos desde el CSV y le da formato a cada columna tanto a fecha y hora.
 * @author Juan Bladimir Romero Collazos.
 */

public class AppSismos {
    /**
     * Nos permite vizualizar cada dato de forma iterativa
     * @throws IOException  Significa que el método puede generar una excepción de entrada y salida.
     */
    public static void main (String [] arg) throws IOException{
        // Este código nos permite visualizar los datos (Autor: Juan Bladimir Romero Collazos)
        String fileName = "./src/main/resources/data.csv";
        DataSismos[] lista = IOSismos.loadDataSismos(fileName);
        for (DataSismos validate : lista) {
            System.out.println(validate);
        }
    }
}
