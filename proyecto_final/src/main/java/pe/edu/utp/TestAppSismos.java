package pe.edu.utp;

import pe.edu.utp.util.*;

import java.io.IOException;

/**
 * Esta clase genera objetos extraídos desde el CSV y les da formato a cada columna, incluyendo fecha y hora.
 * @author Juan Bladimir Romero Collazos, Daniel Ramos Marrufo.
 */

public class TestAppSismos {
    /**
     * Nos permite visualizar cada dato de forma iterativa.
     * @throws IOException  Significa que el método puede generar una excepción de entrada y salida.
     */
    public static void main(String[] args) throws IOException {
        // Este código nos permite visualizar los datos (Autor: Juan Bladimir Romero Collazos)
        String fileName = "./src/main/resources/data.csv";
        int year = 1960;
        int validate = (Boolean.parseBoolean(ValidateDateYear.validateYear(year))) ? year : 0;
        DataSismos[] lista = IOSismos.loadDataSismos(fileName, validate);
        String reporteHTML = IOCreateArchive.makeReport(IOCreateArchive.TIPO.HTML5, lista).toString();
        String reporteHTMLGraficos = IOCreateArchive.makeReport(IOCreateArchive.TIPO.HTML5GRAFICOS, lista).toString();
        String archivoJSPorcentaje = IOCreateArchive.makeJs(IOCreateArchive.NOMBREJS.PORCENTAJES).toString();
        String archivoJSGraficos = IOCreateArchive.makeJs(IOCreateArchive.NOMBREJS.GRAFICOS).toString();
        String fileOutHTML = "./src/main/resources/report/demo.html";
        String fileOutHTMLGraficos = "./src/main/resources/report/demo_graficos.html";
        String fileOutJsPorcentaje = "./src/main/resources/report/js/porcentaje.js";
        String fileOutJsGrafico = "./src/main/resources/report/js/graficos_demo.js";

        FileDeleter.deleteFile(fileOutHTML);
        FileDeleter.deleteFile(fileOutHTMLGraficos);
        FileDeleter.deleteFile(fileOutJsGrafico);
        FileDeleter.deleteFile(fileOutJsPorcentaje);

        try {
            TextUTP.append(reporteHTML, fileOutHTML);
            System.out.println("El archivo fue creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        try {
            TextUTP.append(reporteHTMLGraficos, fileOutHTMLGraficos);
            System.out.println("El archivo fue creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        try {
            TextUTP.append(archivoJSPorcentaje, fileOutJsPorcentaje);
            System.out.println("El archivo fue creado exitosamente.");
        } catch (IOException e) {
            String msg = "Error al crear el archivo: " + e.getMessage();
            GeneratorLog.catchLog(msg, GeneratorLog.LEVEL.ERROR);
        }

        try {
            TextUTP.append(archivoJSGraficos, fileOutJsGrafico);
            System.out.println("El archivo fue creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        // Prueba ASCII

        String reporteASCII = IOCreateArchive.makeReport(IOCreateArchive.TIPO.ASCII, lista).toString();
        System.out.println(reporteASCII);
    }
}
