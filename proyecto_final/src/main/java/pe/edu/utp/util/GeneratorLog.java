package pe.edu.utp.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase permite generar el archivo *.log y los almacena en carpetas especificas para cada tipo de nivel.
 * @author Juan Bladimir Romero Collazos
 * @version 1.0.0
 */
public class GeneratorLog {
    /*
    * DEBUG: Trazas de la aplicación en depuración.
    * INFO: Información.
    * WARN: Advertencia (posible fallo).
    * ERROR: Error del sistema.
    * FATAL: Error bloqueante que puede tener efectos secundarios en el sistema
    *         (por ejemplo, no poder conectarnos a una base de datos generará probablemente
    *         un bloqueo de muchas funcionalidades del sistema).
     */

    public enum LEVEL {DEBUG, INFO, WARN, ERROR, FATAL}

    /**
     * Este método nos permite crear archivos *.log.
     * @param msg Este es el mensaje que se mostrara.
     * @param level Este es el nivel de log que se está presentando.
     */
    public static void catchLog (String msg, LEVEL level) throws IOException {
        switch (level) {
            case DEBUG -> {
                LocalDateTime ldt = LocalDateTime.now();
                String path = ".\\src\\main\\resources\\log\\debug\\DebugReports_";
                String timeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm"));
                String dateTimeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                String extension = ".log";
                String pathFile = path.concat(timeEvent).concat(extension);
                String event = String.format("|\t[%20s]\t  |\t%10s\t  |\t%57s\t\t|\r\n", dateTimeEvent, level, msg);
                TextUTP.append(event, pathFile);
            }
            case INFO -> {
                LocalDateTime ldt = LocalDateTime.now();
                String path = ".\\src\\main\\resources\\log\\info\\ReportingReports_";
                String timeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm"));
                String dateTimeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                String extension = ".log";
                String pathFile = path.concat(timeEvent).concat(extension);
                String event = String.format("|\t[%20s]\t  |\t%10s\t  |\t%57s\t\t|\r\n", dateTimeEvent, level, msg);
                TextUTP.append(event, pathFile);
            }
            case WARN -> {
                LocalDateTime ldt = LocalDateTime.now();
                String path = ".\\src\\main\\resources\\log\\info\\warning\\WarningReports_";
                String timeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm"));
                String dateTimeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                String extension = ".log";
                String pathFile = path.concat(timeEvent).concat(extension);
                String event = String.format("|\t[%20s]\t  |\t%10s\t  |\t%57s\t\t|\r\n", dateTimeEvent, level, msg);
                TextUTP.append(event, pathFile);
            }
            case ERROR -> {
                LocalDateTime ldt = LocalDateTime.now();
                String path = ".\\src\\main\\resources\\log\\error\\CrashReports_";
                String timeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm"));
                String dateTimeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                String extension = ".log";
                String pathFile = path.concat(timeEvent).concat(extension);
                String event = String.format("|\t[%20s]\t  |\t%10s\t  |\t%57s\t\t|\r\n", dateTimeEvent, level, msg);
                TextUTP.append(event, pathFile);
            }
            case FATAL -> {
                LocalDateTime ldt = LocalDateTime.now();
                String path = ".\\src\\main\\resources\\log\\info\\fatal\\FatalReports_";
                String timeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm"));
                String dateTimeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                String extension = ".log";
                String pathFile = path.concat(timeEvent).concat(extension);
                String event = String.format("|\t[%20s]\t  |\t%10s\t  |\t%57s\t\t|\r\n", dateTimeEvent, level, msg);
                TextUTP.append(event, pathFile);
            }
        }
    }
}
