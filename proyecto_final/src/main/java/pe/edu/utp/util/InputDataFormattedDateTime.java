package pe.edu.utp.util;

import pe.edu.utp.ValidationData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Este código permite darle formato a las fechas y a las horas del archivo CSV y algunas funcionalidades extras.
 * @author Juan Bladimir Romero Collazos y Daniel Ramos Marrufo.
 * @version 0.0.2V
 */

public class InputDataFormattedDateTime {
    public static ValidationData[] dateValidateData(String filename, int yearUTC) throws IOException {
        String[] lineas = TextUTP.readlinesAsArray(filename);
        ValidationData[] lista = new ValidationData[lineas.length - 1];
        int nd = 0;

        // Crear un formato de fecha para parsear las fechas UTC
        DateTimeFormatter dftFechaUTC = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Crear un formato de fecha para parsear las fechas corte

        DateTimeFormatter dftFechaCorte = DateTimeFormatter.ofPattern("yyyyddMM");

        // Crear un formato de feha para parsear la hora UTC

        DateTimeFormatter dftHoraUTC = DateTimeFormatter.ofPattern("HHmmss");

        // Crear un formato de fecha para imprimir en el formato deseado
        DateTimeFormatter outputDft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputDftHoraUTC = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            // Obtener el valor de la columna "FECHA_UTC"
            String fechaUtcString = fragmentos[1].replaceAll("\"", "").trim();

            // Obtener el valor de la columna "FECHA_CORTE"
            String fechaCorteString = fragmentos[7].replaceAll("\"", "").trim();

            // Obtener el valor de la columna "HORA_UTC"

            String horaUtcString = fragmentos[2].replaceAll("\"", "").trim();

            // Parsear la fecha UTC
            LocalDate fechaUtc;

            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
            } catch (java.time.format.DateTimeParseException e) {
                // Manejar el error de parseo de fecha según sea necesario
                System.err.println("Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString);
                continue;  // Saltar a la siguiente línea
            }

            LocalDate fechaFinalizado;
            try {
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
            } catch (java.time.format.DateTimeParseException e) {
                // Manejar el error de parseo de fecha según sea necesario
                System.err.println("Error al parsear la fecha Corte en la línea " + (i + 1) + ": " + fechaCorteString);
                continue;  // Saltar a la siguiente línea
            }

            LocalTime HoraUTC;
            try {
                HoraUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (java.time.format.DateTimeParseException e) {
                // Manejar el error de parseo de fecha según sea necesario
                System.err.println("Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString);
                continue;  // Saltar a la siguiente línea
            }

            // Obtener el año de la fecha UTC
            int fechaUtcYear = fechaUtc.getYear();
            // Filtrar por año
            if (fechaUtcYear == yearUTC) {
                long id = Long.parseLong(fragmentos[0].replaceAll("\"", "").trim());
                double latitud = Double.parseDouble(fragmentos[3].replaceAll("\"", "").trim());
                double longitud = Double.parseDouble(fragmentos[4].replaceAll("\"", "").trim());
                int profundidad = (int) Double.parseDouble(fragmentos[5].replaceAll("\"", "").trim());
                double magnitud = Double.parseDouble(fragmentos[6].replaceAll("\"", "").trim());

                // Formatear la fecha UTC al formato deseado antes de asignarla
                String formattedFechaUtc = fechaUtc.format(outputDft);
                String formattedFechaFinalizado = fechaFinalizado.format(outputDft);
                String formattedHoraUTC = HoraUTC.format(outputDftHoraUTC);

                lista[nd++] = new ValidationData(id, formattedFechaUtc, formattedHoraUTC, latitud,
                        longitud, profundidad, magnitud, formattedFechaFinalizado);
            }
        }

        // Redimensionar el arreglo si no se encontraron suficientes elementos válidos
        if (nd < lista.length) {
            ValidationData[] validDataArray = new ValidationData[nd];
            System.arraycopy(lista, 0, validDataArray, 0, nd);
            lista = validDataArray;
        }

        return lista;
    }
}
