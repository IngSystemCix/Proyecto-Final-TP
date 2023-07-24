package pe.edu.utp.util;

/**
 * Esta clase permite validar el año ingresado.
 * @author Daniel Ramos Marrufo
 */
public class ValidateDateYear {
    /**
     * Este método nos permite validar si esta en un rango de años en específico.
     * @param yearUTC El año.
     * @return Retorna un valor true o false.
     */
    public static String validateYear (int yearUTC) {
        return (2021 >= yearUTC && yearUTC >= 1960) ? "true" : "false";
    }

    /**
     * Método para que nos devuelva si el año es valido.
     * @param year Año.
     * @return retorna el año despues de ser validado.
     */
    public static int validate (int year) {
        return (Boolean.parseBoolean(ValidateDateYear.validateYear(year))) ? year : 0;
    }

    /**
     * Método para que nos devuelva si el año es valido.
     * @param year1 Año.
     * @return retorna el año despues de ser validado.
     */
    public static int validate1 (int year1) {
        return (Boolean.parseBoolean(ValidateDateYear.validateYear(year1))) ? year1 : 0;
    }
}
