package pe.edu.utp.util;

import pe.edu.utp.DataSismos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase proporciona funcionalidades para cargar y formatear datos de sismos desde un archivo CSV.
 * También ofrece algunas funcionalidades adicionales.
 * @author Juan Bladimir Romero Collazos.
 * Versión: 1.0.1
 */
public class IOSismos {
    public static DataSismos[] loadDataSismos(String filename) throws IOException {
        return loadDataSismos(filename, -1);
    }

    public static DataSismos[] loadDataSismos(String filename, int yearUTC) throws IOException {
        String[] lineas = TextUTP.readlinesAsArray(filename, TextUTP.OS.WINDOWS);
        DataSismos[] lista = new DataSismos[lineas.length - 1];
        int nd = 0;

        // Crear un formato de fecha para parsear las fechas UTC
        DateTimeFormatter dftFechaUTC = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Crear un formato de fecha para parsear las fechas corte
        DateTimeFormatter dftFechaCorte = DateTimeFormatter.ofPattern("yyyyddMM");

        // Crear un formato de hora para parsear la hora UTC
        DateTimeFormatter dftHoraUTC = DateTimeFormatter.ofPattern("HHmmss");

        // Crear un formato de fecha para imprimir en el formato deseado
        DateTimeFormatter outputDft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputDftHoraUTC = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            // Obtener el valor de la columna "FECHA_UTC"
            String fechaUtcString = cleanString(fragmentos[1]);

            // Obtener el valor de la columna "FECHA_CORTE"
            String fechaCorteString = cleanString(fragmentos[7]);

            // Obtener el valor de la columna "HORA_UTC"
            String horaUtcString = cleanString(fragmentos[2]);

            // Parsear la fecha UTC
            LocalDate fechaUtc;
            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString);
                continue;  // Saltar a la siguiente línea
            }

            // Parsear la fecha de corte
            LocalDate fechaFinalizado;
            try {
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString);
                continue;  // Saltar a la siguiente línea
            }

            // Parsear la hora UTC
            LocalTime horaUTC;
            try {
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString);
                continue;  // Saltar a la siguiente línea
            }

            // Filtrar por año si se proporciona uno válido
            if (yearUTC > 0 && fechaUtc.getYear() != yearUTC) {
                continue; // Saltar a la siguiente línea
            }

            long id = Long.parseLong(cleanString(fragmentos[0]));
            double latitud = Double.parseDouble(cleanString(fragmentos[3]));
            double longitud = Double.parseDouble(cleanString(fragmentos[4]));
            int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));
            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

            // Formatear la fecha UTC y la hora al formato deseado antes de asignarlas
            String formattedFechaUtc = fechaUtc.format(outputDft);
            String formattedFechaFinalizado = fechaFinalizado.format(outputDft);
            String formattedHoraUTC = horaUTC.format(outputDftHoraUTC);

            lista[nd++] = new DataSismos(id, formattedFechaUtc, formattedHoraUTC, latitud,
                    longitud, profundidad, magnitud, formattedFechaFinalizado);
        }

        // Redimensionar el arreglo si no se encontraron suficientes elementos válidos
        if (nd < lista.length) {
            DataSismos[] validDataArray = new DataSismos[nd];
            System.arraycopy(lista, 0, validDataArray, 0, nd);
            lista = validDataArray;
        }

        return lista;
    }

    private static String cleanString(String input) {
        return input.replaceAll("\"", "").trim();
    }
}