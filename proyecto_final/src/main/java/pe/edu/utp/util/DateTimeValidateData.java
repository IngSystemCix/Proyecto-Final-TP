package pe.edu.utp.util;

import pe.edu.utp.ValidationData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeValidateData {
    public static ValidationData[] dateValidateData(String filename, int yearUTC) throws IOException {
        String[] lineas = TextUTP.readlinesAsArray(filename);
        ValidationData[] lista = new ValidationData[lineas.length - 1];
        int nd = 0;

        // Crear un formato de fecha para parsear las fechas UTC
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Crear un formato de fecha para imprimir en el formato deseado
        DateTimeFormatter outputDft = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            // Obtener el valor de la columna "FECHA_UTC"
            String fechaUtcString = fragmentos[1].replaceAll("\"", "").trim();

            // Parsear la fecha UTC
            LocalDate fechaUtc;
            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dft);
            } catch (java.time.format.DateTimeParseException e) {
                // Manejar el error de parseo de fecha según sea necesario
                System.err.println("Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString);
                continue;  // Saltar a la siguiente línea
            }

            // Obtener el año de la fecha UTC
            int fechaUtcYear = fechaUtc.getYear();

            // Filtrar por año
            if (fechaUtcYear == yearUTC) {
                long id = Long.parseLong(fragmentos[0].replaceAll("\"", "").trim());
                String horaUtc = fragmentos[2].replaceAll("\"", "").trim();
                double latitud = Double.parseDouble(fragmentos[3].replaceAll("\"", "").trim());
                double longitud = Double.parseDouble(fragmentos[4].replaceAll("\"", "").trim());
                int profundidad = (int) Double.parseDouble(fragmentos[5].replaceAll("\"", "").trim());
                double magnitud = Double.parseDouble(fragmentos[6].replaceAll("\"", "").trim());
                String fechaCorte = fragmentos[7].replaceAll("\"", "").trim();

                // Formatear la fecha UTC al formato deseado antes de asignarla
                String formattedFechaUtc = fechaUtc.format(outputDft);

                lista[nd++] = new ValidationData(id, formattedFechaUtc, horaUtc, latitud, longitud, profundidad, magnitud, fechaCorte);
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
