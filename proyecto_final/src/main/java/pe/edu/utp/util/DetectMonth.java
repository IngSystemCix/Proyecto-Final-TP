package pe.edu.utp.util;

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
    protected static int validationMonth (String mes) throws IllegalArgumentException{

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
            default -> throw new IllegalArgumentException("Mes ingresado incorrecto");
        }
        return valueMonth;
    }
}
