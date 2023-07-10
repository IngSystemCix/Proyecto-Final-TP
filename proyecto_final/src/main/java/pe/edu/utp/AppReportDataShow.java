package pe.edu.utp;
import pe.edu.utp.util.InputDataFormattedDateTime;

import java.io.IOException;

/**
 * Esta clase genera objetos substraidos desde el CSV y le da formato a cada columna tanto a fecha y hora.
 * @author Juan Bladimir Romero Collazos.
 */

public class AppReportDataShow {
    /**
     * Nos permite vizualizar cada dato de forma iterativa
     * @throws IOException  Significa que el método puede generar una excepción de entrada y salida.
     */
    public static void showData() throws IOException{
        // Este código nos permite visualizar los datos (Autor: Juan Bladimir Romero Collazos)
        String fileName = "./src/main/resources/data.csv";
        ValidationData [] lista = InputDataFormattedDateTime.dateValidateData(fileName, 2000);
        for (ValidationData validate : lista) {
            System.out.println(validate);
        }
    }
}
