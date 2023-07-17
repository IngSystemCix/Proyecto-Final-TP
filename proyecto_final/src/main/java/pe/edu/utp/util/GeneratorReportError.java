package pe.edu.utp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase permite generar el reporte de errores.
 * @author Juan Bladimir Romero Collazos
 * @version 1.0.0
 */
public class GeneratorReportError {
    protected static void capturarErrores (String error) {
        LocalDateTime ldt = LocalDateTime.now();
        String path = "./src/main/resources/error/error_";
        String fechaError = ldt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH*mm*ss"));
        String extension = ".log";
        String pathFile = path.concat(fechaError).concat(extension);
    }
}
