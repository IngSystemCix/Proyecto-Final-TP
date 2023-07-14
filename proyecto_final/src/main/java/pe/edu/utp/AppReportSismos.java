package pe.edu.utp;

import java.util.Scanner;

/**
 * Esta clase nos permite generar el reporte ASCII o HTML.
 * @author Juan Bladimir Romero Collazos, Daniel Ramos Marrufo y Hugo Fupuy Chanamé.
 */

public class AppReportSismos {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
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
                    // Configuración de tu la opción "A" (Autor: )
                    // Función para validar que opción de reporte (Autor: Juan Bladimir Romero Collazos)
                    String resultado = reportASCIIorHTML5();
                    System.out.println(resultado);
                }
                case "B" -> {
                    // Configuración de tu la opción "B" (Autor: )
                    // Función para validar que opción de reporte (Autor: Juan Bladimir Romero Collazos)
                    String resultado = reportASCIIorHTML5();
                    System.out.println(resultado);
                }
                case "C" -> {
                    // Configuración de tu la opción "C" (Autor: )
                    // Función para validar que opción de reporte (Autor: Juan Bladimir Romero Collazos)
                    String resultado = reportASCIIorHTML5();
                    System.out.println(resultado);
                }
                case "D" -> {
                    // Configuración de tu la opción "D" (Autor: )
                    // Función para validar que opción de reporte (Autor: Juan Bladimir Romero Collazos)
                    String resultado = reportASCIIorHTML5();
                    System.out.println(resultado);
                }
                case "E" -> {
                    // Configuración de tu la opción "E" (Autor: )
                    // Función para validar que opción de reporte (Autor: Juan Bladimir Romero Collazos)
                    String resultado = reportASCIIorHTML5();
                    System.out.println(resultado);
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
    /**
     * Creacion de la función para poder hacer la pregunta de que tipo de reporte desea el usuario.
     * @return Si la opción es 0 o 1 para mandar un mensaje por consola.
     */
    /* (Autor: Hugo Fupuy Chanamé)        */
    public static String reportASCIIorHTML5 () {
        Scanner input = new Scanner(System.in);
        String pregunta = """
                ¿Cómo desea imprimir su reporte (ASCCI o HTML5)?
                0) ASCII
                1) HTML5
                Selecciona una opción:
                """;
        System.out.println(pregunta);
        int opc = input.nextInt();input.nextLine();
        return (opc == 0) ? "Espere porfavor se esta creado el reporte ASCII..." :
                (opc == 1) ? "Espere porfavor se esta creado el reporte HTML..." :
                        "Usted a ingresado una opción invalida";
    }
}
