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
        int year1;
        double mag;
        double mag1;
        String month;
        int day;
        boolean breakProgress = false;
        String fileName = "./src/main/resources/data.csv";
        String fileOutHTML = "./src/main/resources/report/demo.html";
        String fileOutJsPorcentaje = "./src/main/resources/report/js/porcentaje.js";
        String fileOutHTMLGraficos = "./src/main/resources/report/demo_graficos.html";
        String fileOutJsGrafico = "./src/main/resources/report/js/graficos_demo.js";
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
                    // Configuración de tu la opción "A" (Autor:Hugo Fupuy Chanamé);
                    do {
                        System.out.println("Ingrese el primer año de deseas que comience a filtrar: ");
                        year = input.nextInt();input.nextLine();
                        System.out.println("Ingrese el segundo año de deseas que termine de filtrar: ");
                        year1 = input.nextInt(); input.nextLine();
                    } while (Boolean.parseBoolean(ValidateDateYear.validateYear(year + year1))) ;
                    do {
                        System.out.println(preguntaASCIIOrHTML5);
                        int rpta1 = input.nextInt();
                        input.nextLine();
                        switch (rpta1) {
                            case 0 -> {
                                breakProgress = false;
                                System.out.println("Creando reporte ASCII: ");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName,
                                        ValidateDateYear.validate(year),
                                        ValidateDateYear.validate1(year1));
                                String reporteASCII = IOCreateArchive.makeReport(IOCreateArchive.TIPO.ASCII, lista).toString();
                                System.out.println(reporteASCII);
                            }
                            case 1 -> {
                                breakProgress = false;
                                System.out.println("Creando Reporte HTML5:");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName,
                                        ValidateDateYear.validate(year),
                                        ValidateDateYear.validate1(year1));

                                String reporteHTML = IOCreateArchive.makeReport(IOCreateArchive.TIPO.HTML5, lista).toString();
                                String archivoJSPorcentaje = IOCreateArchive.makeJs(IOCreateArchive.NOMBREJS.PORCENTAJES).toString();
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
                                breakProgress = true;
                            }

                        }

                    }while (breakProgress);
                }
                case "B" -> {
                    // Configuración de tu la opción "B" (Autor: Daniel Ramos Marrufo);
                    do {
                        System.out.println("Ingrese el año que desea filtrar: ");
                        year = input.nextInt();input.nextLine();
                    }while (!Boolean.parseBoolean(ValidateDateYear.validateYear(year)));

                    do {
                        System.out.println("Ingresa el mes: ");
                        month = input.nextLine();
                        continuar = DetectMonth.validationMonthTrueFalse(month);
                    }while (continuar);

                    do {
                        System.out.println(preguntaASCIIOrHTML5);
                        int rpta = input.nextInt();input.nextLine();
                        switch (rpta) {
                            case 0 -> {
                                breakProgress = false;
                                System.out.println("Creando Reporte ASCII:");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName, month,
                                        ValidateDateYear.validate(year));
                                String reporteASCII = IOCreateArchive.makeReport(IOCreateArchive.TIPO.ASCII, lista).toString();
                                System.out.println(reporteASCII);
                            }case 1 -> {
                                breakProgress = false;
                                System.out.println("Creando Reporte HTML5:");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName, month,
                                        ValidateDateYear.validate(year));

                                String reporteHTML = IOCreateArchive.makeReport(IOCreateArchive.TIPO.HTML5, lista).toString();
                                String archivoJSPorcentaje = IOCreateArchive.makeJs(IOCreateArchive.NOMBREJS.PORCENTAJES).toString();
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
                                breakProgress = true;
                            }
                        }
                    }while (breakProgress);
                }
                case "C" -> {
                    // Configuración de tu la opción "C" (Autor:Hugo Fupuy Chanamé)
                    do {
                        System.out.println("Ingrese el año que deseas filtrar: ");
                        year = input.nextInt();input.nextLine();
                        System.out.println("Ingrese la primera magnitud que desea que comience a filtrar: ");
                        mag = input.nextDouble(); input.nextLine();
                        System.out.println("Ingrese la segunda magnitud que desea que termine de filtrar: ");
                        mag1 = input.nextDouble(); input.nextLine();
                    } while (!Boolean.parseBoolean(ValidateDateYear.validateYear(year)));
                    do {
                        System.out.println("Ingresa el mes: ");
                        month = input.nextLine();
                        continuar = DetectMonth.validationMonthTrueFalse(month);
                    }while (continuar);
                    do {
                        System.out.println(preguntaASCIIOrHTML5);
                        int rpta2 = input.nextInt();input.nextLine();
                        switch (rpta2) {
                            case 0 -> {
                                System.out.println("Creando Reporte ASCII: ");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName,month, mag, mag1,
                                        ValidateDateYear.validate(year));
                                String reporteASCII = IOCreateArchive.makeReport(IOCreateArchive.TIPO.ASCII, lista).toString();
                                System.out.println(reporteASCII);
                            }case 1 -> {
                                System.out.println("Creando Reporte HTML5: ");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName,month, mag, mag1,
                                        ValidateDateYear.validate(year));
                                String reporteHTML = IOCreateArchive.makeReport(IOCreateArchive.TIPO.HTML5, lista).toString();
                                String archivoJSPorcentaje = IOCreateArchive.makeJs(IOCreateArchive.NOMBREJS.PORCENTAJES).toString();
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
                            }default -> {
                                System.out.println("Opción incorrecta, vuelva a intentarlo...");
                                breakProgress = true;
                            }
                        }
                    }while (breakProgress);
                }
                case "D" -> {
                    // Configuración de tu la opción "D" (Autor: Daniel Ramos Marrufo)
                    do {
                        System.out.println("Ingrese el año que desea filtrar: ");
                        year = input.nextInt();input.nextLine();
                        System.out.println("Ingrese el día: ");
                        day = input.nextInt(); input.nextLine();
                    }while (!Boolean.parseBoolean(ValidateDateYear.validateYear(year)));
                    do {
                        System.out.println("Ingresa el mes: ");
                        month = input.nextLine();
                        continuar = DetectMonth.validationMonthTrueFalse(month);
                    }while (continuar);
                    do {
                        System.out.println(preguntaASCIIOrHTML5);
                        int rpta = input.nextInt();input.nextLine();
                        switch (rpta) {
                            case 0 -> {
                                breakProgress = false;
                                System.out.println("Creando Reporte ASCII: ");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName, day, month, year);
                                String reporteASCII = IOCreateArchive.makeReport(IOCreateArchive.TIPO.ASCII, lista).toString();
                                System.out.println(reporteASCII);
                            }case 1 -> {
                                breakProgress = false;
                                System.out.println("Creando Reporte HTML5: ");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName, day, month,
                                        ValidateDateYear.validate(year));
                                String reporteHTML = IOCreateArchive.makeReport(IOCreateArchive.TIPO.HTML5, lista).toString();
                                String archivoJSPorcentaje = IOCreateArchive.makeJs(IOCreateArchive.NOMBREJS.PORCENTAJES).toString();
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
                                breakProgress = true;
                            }
                        }
                    }while (breakProgress);
                }
                case "E" -> {
                    // Configuración de tu la opción "E" (Autor: Hugo Fupuy Chanamé)
                    do {
                        System.out.println("Ingrese el primer año de deseas que comience a filtrar: ");
                        year = input.nextInt();input.nextLine();
                        System.out.println("Ingrese el segundo año de deseas que termine de filtrar: ");
                        year1 = input.nextInt(); input.nextLine();
                    } while (Boolean.parseBoolean(ValidateDateYear.validateYear(ValidateDateYear.validate(year)
                            + ValidateDateYear.validate1(year1)))) ;
                    do {
                        System.out.println(preguntaASCIIOrHTML5);
                        int rpta1 = input.nextInt();
                        input.nextLine();
                        switch (rpta1) {
                            case 0 -> {
                                breakProgress = false;
                                System.out.println("Creando reporte ASCII: ");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName, ValidateDateYear.validate(year),
                                        ValidateDateYear.validate1(year1));
                                String reporteASCII = IOCreateArchive.makeReport(IOCreateArchive.TIPO.ASCII, lista).toString();
                                System.out.println(reporteASCII);
                            }
                            case 1 -> {
                                breakProgress = false;
                                System.out.println("Creando Reporte HTML5: ");
                                DataSismos[] lista = IOSismos.loadDataSismos(fileName, ValidateDateYear.validate(year),
                                        ValidateDateYear.validate1(year1));
                                String reporteHTMLGraficos = IOCreateArchive.makeReport(IOCreateArchive.TIPO.HTML5GRAFICOS, lista).toString();
                                String archivoJSPorcentaje = IOCreateArchive.makeJs(IOCreateArchive.NOMBREJS.PORCENTAJES).toString();
                                String archivoJSGraficos = IOCreateArchive.makeJs(IOCreateArchive.NOMBREJS.GRAFICOS).toString();
                                FileDeleter.deleteFile(fileOutJsPorcentaje);
                                FileDeleter.deleteFile(fileOutHTMLGraficos);
                                FileDeleter.deleteFile(fileOutJsGrafico);
                                try {
                                    TextUTP.append(reporteHTMLGraficos, fileOutHTMLGraficos);
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

                                try {
                                    TextUTP.append(archivoJSGraficos, fileOutJsGrafico);
                                    System.out.println("El archivo fue creado exitosamente.");
                                } catch (IOException e) {
                                    String msg = "Error al crear el archivo: " + e.getMessage();
                                    GeneratorLog.catchLog(msg, GeneratorLog.LEVEL.ERROR);
                                }
                            }
                            default -> {
                                System.out.println("Opción incorrecta, vuelva a intentarlo...");
                                breakProgress = true;
                            }

                        }

                    }while (breakProgress);
                }
                case "F" -> {
                    System.out.println("Gracias por usar el programa");
                    continuar = true; // Salir del programa
                }
                default -> System.out.println("Usted no a ingresado una opción valida vuelva a intentarlo nuevamente");
            }
        }while (!continuar);
    }
}
