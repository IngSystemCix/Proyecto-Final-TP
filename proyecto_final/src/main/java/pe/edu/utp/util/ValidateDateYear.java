package pe.edu.utp.util;

/**
 * Esta clase permite validar el año ingresado.
 * @author Daniel Ramos Marrufo
 */
public class ValidateDateYear {
    public static String validateYear (int yearUTC) {
        return (2021 >= yearUTC && yearUTC >= 1960) ? "true" : "false";
    }
    public static int validate (int year) {
        return (Boolean.parseBoolean(ValidateDateYear.validateYear(year))) ? year : 0;
    }

    public static int validate1 (int year1) {
        return (Boolean.parseBoolean(ValidateDateYear.validateYear(year1))) ? year1 : 0;
    }
}
