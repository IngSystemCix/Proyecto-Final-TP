package pe.edu.utp;
import pe.edu.utp.util.FileDeleter;
import pe.edu.utp.util.IOReportes;
import pe.edu.utp.util.IOSismos;
import pe.edu.utp.util.TextUTP;

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
        DataSismos[] lista = IOSismos.loadDataSismos(fileName, 1991);
        //        for (DataSismos validate : lista) {
        //            System.out.println(validate);
        //        }
        String reporteHTML = String.valueOf(IOReportes.makeReport(IOReportes.TIPO.HTML5, lista));
        String archivoJS = String.valueOf(IOReportes.makeJs(IOReportes.NOMBREJS.PORCENTAJES));
        String fileOutHTML = "./src/main/resources/report/demo.html";
        String fileOutJs = "./src/main/resources/report/js/porcentaje.js";
        FileDeleter.deleteFile(fileOutHTML);
        FileDeleter.deleteFile(fileOutJs);
        TextUTP.append(reporteHTML, fileOutHTML);

        try {
            TextUTP.append(archivoJS, fileOutJs);
            System.out.println("Archivo creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

    }
}
