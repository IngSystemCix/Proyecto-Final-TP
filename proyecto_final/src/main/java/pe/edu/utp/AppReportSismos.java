package pe.edu.utp;

import pe.edu.utp.util.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * Esta clase nos permite generar el reporte ASCII o HTML.
 * @author Juan Bladimir Romero Collazos, Daniel Ramos Marrufo y Hugo Fupuy Chanamé.
 */

public class AppReportSismos {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int year;
        String month = "";
        boolean rpta_pregunta_ASCCIorHTML5;
        String fileName = "./src/main/resources/data.csv";
        String preguntaASCIIOrHTML5 = """
                ¿Cómo desea imprimir su reporte (ASCCI o HTML5)?
                0) ASCII
                1) HTML5
                Selecciona una opción:
                """;
        String menu = """
                ################################################################################################
                # A. Tabla con el número de eventos sísmicos por año dado un rango de años.                    #
                # B. Tabla con el número de eventos sísmicos por mes dado un año.                              #
                # C. Tabla con el número de eventos sísmicos por mes dados un rango de magnitudes y un año.    #
                # D. Tabla con un reporte personalizado a criterio del grupo.                                  #
                # E. Tabla con un reporte personalizado incluyendo un gráfico estadístico.                     #
                # F. Salir                                                                                     #
                ################################################################################################
                # Elige una opción:                                                                            #
                ################################################################################################
                """;
        // Variable para controlar si se debe continuar o salir del programa (Autor: Juan Bladimir Romero Collazos)
        boolean continuar = true;
        do {
            System.out.println(menu);
            String opc = input.nextLine().toUpperCase();
            switch (opc) {
                case "A" -> {
                    // Configuración de tu la opción "A" (Autor: );
                }
                case "B" -> {
                    // Configuración de tu la opción "B" (Autor: Daniel Ramos Marrufo);
                    do {
                        System.out.println("Ingrese el año que desea filtrar:");
                        year = input.nextInt();input.nextLine();
                        System.out.println("Ingresa el mes");
                        month = input.nextLine();
                    }while (!Boolean.parseBoolean(ValidateDateYear.validateYear(year)));

                    do {
                        System.out.println(preguntaASCIIOrHTML5);
                        int rpta = input.nextInt();input.nextLine();
                        switch (rpta) {
                            case 0 -> {
                                rpta_pregunta_ASCCIorHTML5 = false;
                                System.out.println("Creando Reporte ASCII:");
                                int validate = (Boolean.parseBoolean(ValidateDateYear.validateYear(year))) ? year : 0;
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName, month, validate);
                                String reporteASCII = IOCreateArchive.makeReport(IOCreateArchive.TIPO.ASCII, lista).toString();
                                System.out.println(reporteASCII);
                            }case 1 -> {
                                rpta_pregunta_ASCCIorHTML5 = false;
                                System.out.println("Creando Reporte HTML5:");
                                int validate = (Boolean.parseBoolean(ValidateDateYear.validateYear(year))) ? year : 0;
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName, month, validate);

                                String reporteHTML = IOCreateArchive.makeReport(IOCreateArchive.TIPO.HTML5, lista).toString();
                                String archivoJSPorcentaje = IOCreateArchive.makeJs(IOCreateArchive.NOMBREJS.PORCENTAJES).toString();
                                String fileOutHTML = "./src/main/resources/report/demo.html";
                                String fileOutJsPorcentaje = "./src/main/resources/report/js/porcentaje.js";
                                FileDeleter.deleteFile(fileOutHTML);
                                FileDeleter.deleteFile(fileOutJsPorcentaje);
                                try {
                                    TextUTP.append(reporteHTML, fileOutHTML);
                                    System.out.println("El archivo fue creado exitosamente.");
                                } catch (IOException e) {
                                    String msg = "Error al crear el archivo: " + e.getMessage();
                                    GeneratorLog.catchLog(msg, GeneratorLog.LEVEL.ERROR);
                                }

                                try {
                                    TextUTP.append(archivoJSPorcentaje, fileOutJsPorcentaje);
                                    System.out.println("El archivo fue creado exitosamente.");
                                } catch (IOException e) {
                                    String msg = "Error al crear el archivo: " + e.getMessage();
                                    GeneratorLog.catchLog(msg, GeneratorLog.LEVEL.ERROR);
                                }
                            }
                            default -> {
                                System.out.println("Opción incorrecta, vuelva a intentarlo...");
                                rpta_pregunta_ASCCIorHTML5 = true;
                            }
                        }
                    }while (rpta_pregunta_ASCCIorHTML5);
                    // Configuración de tu la opción "B" (Autor: )
                }
                case "C" -> {
                    // Configuración de tu la opción "C" (Autor: )
                }
                case "D" -> {
                    // Configuración de tu la opción "D" (Autor: )
                }
                case "E" -> {
                    // Configuración de tu la opción "E" (Autor: )
                }
                case "F" -> {
                    System.out.println("Gracias por usar el programa");
                    continuar = false; // Salir del programa
                }
                default -> {
                    System.out.println("Usted no a ingresado una opción valida vuelva a intentarlo nuevamente");
                }
            }
        }while (continuar);
    }
}
