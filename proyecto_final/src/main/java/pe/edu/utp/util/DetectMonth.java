package pe.edu.utp.util;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Esta clase permite poner el nombre del mes en el idioma español y asignarle un valor numérico.
 * @author Juan Bladmir Romero Collazos.
 * @version 1.0.0
 */

public class DetectMonth {
    /**
     * Este método lo que permite es devolver un valor numérico dependiendo del mes.
     * @param mes es el mes que se esta pasando en otra clase.
     * @return retorna el valor numérico del mes.
     * @throws IllegalArgumentException esto es porque estoy manejando excisiones.
     */
    protected static int validationMonth (String mes) throws IllegalArgumentException, IOException {
        String msg = "";
        int valueMonth = 0;
        switch (mes.toUpperCase()) {
            case "ENERO" -> valueMonth = 1;
            case "FEBRERO" -> valueMonth = 2;
            case "MARZO" -> valueMonth = 3;
            case "ABRIL" -> valueMonth = 4;
            case "MAYO" -> valueMonth = 5;
            case "JUNIO" -> valueMonth = 6;
            case "JULIO" -> valueMonth = 7;
            case "AGOSTO" -> valueMonth = 8;
            case "SEPTIEMBRE" -> valueMonth = 9;
            case "OCTUBRE" -> valueMonth = 10;
            case "NOVIEMBRE" -> valueMonth = 11;
            case "DICIEMBRE" -> valueMonth = 12;
            default -> {
                valueMonth = 1;
                msg = "Mes ingresado incorrecto";
                GeneratorLog.catchLog(msg, GeneratorLog.LEVEL.ERROR);
            }
        }
        return valueMonth;
    }

    protected static String monthToNum (int numMonth) throws IOException {
        String catchMonth;
        switch (numMonth) {
            case 1 -> catchMonth = "ENERO";
            case 2 -> catchMonth = "FEBRERO";
            case 3 -> catchMonth = "MARZO";
            case 4 -> catchMonth = "ABRIL";
            case 5 -> catchMonth = "MAYO";
            case 6 -> catchMonth = "JUNIO";
            case 7 -> catchMonth = "JULIO";
            case 8 -> catchMonth = "AGOSTO";
            case 9 -> catchMonth = "SEPTIEMBRE";
            case 10 -> catchMonth = "OCTUBRE";
            case 11 -> catchMonth = "NOVIEMBRE";
            case 12 -> catchMonth = "DICIEMBRE";
            default -> {
                catchMonth = "";
                String msg = "Número de mes erroneo";
                GeneratorLog.catchLog(msg, GeneratorLog.LEVEL.ERROR);
            }
        }
        return catchMonth;
    }

    public static boolean validationMonthTrueFalse(String month) throws IOException {
        return month.toUpperCase().equals(monthToNum(validationMonth(month)));
    }
}
