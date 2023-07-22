package pe.edu.utp.util;

/**
 * Esta clase permite validar el año ingresado.
 * @author Daniel Ramos Marrufo
 */
public class ValidateDateYear {
    public static String validateYear (int yearUTC) {
        return (2021 >= yearUTC && yearUTC >= 1960) ? "true" : "false";
    }
}
