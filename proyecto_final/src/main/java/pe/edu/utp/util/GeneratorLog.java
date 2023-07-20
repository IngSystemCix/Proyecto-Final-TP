package pe.edu.utp.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase permite generar el archivo *.log.
 * @author Juan Bladimir Romero Collazos
 * @version 1.0.0
 */
public class GeneratorLog {
    public enum LEVEL {DEBUG, INFO, WARN, ERROR, FATAL}
    protected static void catchLog (String msg, LEVEL level) throws IOException {
        LocalDateTime ldt = LocalDateTime.now();
        String path = ".\\src\\main\\resources\\log\\CrashReports_";
        String timeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy_MM_dd__hh_mm"));
        String dateTimeEvent = ldt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));
        String extension = ".log";
        String pathFile = path.concat(timeEvent).concat(extension);
        String event = String.format("|%22s\t  |\t%10s\t  |\t%57s\t\t|\r\n", dateTimeEvent, level, msg);
        TextUTP.append(event, pathFile);
    }
}
