package pe.edu.utp.util;

import pe.edu.utp.DataSismos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Esta clase proporciona funcionalidades para cargar y formatear datos de sismos desde un archivo CSV.
 * También ofrece algunas funcionalidades adicionales.
 * @author Juan Bladimir Romero Collazos.
 * @version 1.0.1
 */

public class IOSismos {
    /**
     * Este método permite filtrar todos los datos de la data.
     * @param filename nombre del archivo.
     * @return va retornar la lista con todos los datos.
     * @throws IOException Controla las excepciones.
     */
    public static DataSismos[] loadDataSismos(String filename) throws IOException {
        return loadDataSismos(filename, -1);
    }

    /**
     * Este metodo nos permite filtrar everything related to this year.
     * @param filename es el nombre del archivo.
     * @param yearUTC es el año que se toma de referencia para el filtrado.
     * @return va a retornar la lista de los datos filtrados.
     * @throws IOException Controla las excepciones.
     */
    public static DataSismos[] loadDataSismos(String filename, int yearUTC) throws IOException {
        String[] lineas = TextUTP.readlinesAsArray(filename, TextUTP.OS.WINDOWS);
        DataSismos[] lista = new DataSismos[lineas.length - 1];
        int nd = 0;

        DateTimeFormatter dftFechaUTC = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dftFechaCorte = DateTimeFormatter.ofPattern("yyyyddMM");
        DateTimeFormatter dftHoraUTC = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter outputDft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputDftHoraUTC = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            String fechaUtcString = cleanString(fragmentos[1]);
            String fechaCorteString = cleanString(fragmentos[7]);
            String horaUtcString = cleanString(fragmentos[2]);

            LocalDate fechaUtc;
            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString);
                continue;
            }

            LocalDate fechaFinalizado;
            try {
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString);
                continue;
            }

            LocalTime horaUTC;
            try {
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString);
                continue;
            }

            if (yearUTC >= 1960 && fechaUtc.getYear() != yearUTC) {
                continue;
            }

            long id = Long.parseLong(cleanString(fragmentos[0]));
            double latitud = Double.parseDouble(cleanString(fragmentos[3]));
            double longitud = Double.parseDouble(cleanString(fragmentos[4]));
            int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));
            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

            String formattedFechaUtc = fechaUtc.format(outputDft);
            String formattedFechaFinalizado = fechaFinalizado.format(outputDft);
            String formattedHoraUTC = horaUTC.format(outputDftHoraUTC);

            lista[nd++] = new DataSismos(id, formattedFechaUtc, formattedHoraUTC, latitud,
                    longitud, profundidad, magnitud, formattedFechaFinalizado);
        }

        if (nd < lista.length) {
            DataSismos[] validDataArray = new DataSismos[nd];
            System.arraycopy(lista, 0, validDataArray, 0, nd);
            lista = validDataArray;
        }

        return lista;
    }

    /**
     * Este método lo que permite realizar es que demos un rango de años.
     * @param filename Es el nombre del archivo que contiene la data.
     * @param yearUTCIncial Es el año de inicio del rango.
     * @param yearUTCFinal Es el año de fin del rango.
     * @return Retorna la lista de los datos filtrados.
     * @throws IOException Controla las excepciones.
     */
    public static DataSismos[] loadDataSismos(String filename, int yearUTCIncial, int yearUTCFinal) throws IOException {
        String[] lineas = TextUTP.readlinesAsArray(filename, TextUTP.OS.WINDOWS);
        DataSismos[] lista = new DataSismos[lineas.length - 1];
        int nd = 0;

        DateTimeFormatter dftFechaUTC = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dftFechaCorte = DateTimeFormatter.ofPattern("yyyyddMM");
        DateTimeFormatter dftHoraUTC = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter outputDft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputDftHoraUTC = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            String fechaUtcString = cleanString(fragmentos[1]);
            String fechaCorteString = cleanString(fragmentos[7]);
            String horaUtcString = cleanString(fragmentos[2]);

            LocalDate fechaUtc;
            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString);
                continue;
            }

            LocalDate fechaFinalizado;
            try {
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString);
                continue;
            }

            LocalTime horaUTC;
            try {
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString);
                continue;
            }

            if (yearUTCIncial >= 1960 && yearUTCFinal >= yearUTCIncial && (fechaUtc.getYear() < yearUTCIncial
                    || fechaUtc.getYear() > yearUTCFinal)) {
                continue;
            }

            long id = Long.parseLong(cleanString(fragmentos[0]));
            double latitud = Double.parseDouble(cleanString(fragmentos[3]));
            double longitud = Double.parseDouble(cleanString(fragmentos[4]));
            int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));
            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

            String formattedFechaUtc = fechaUtc.format(outputDft);
            String formattedFechaFinalizado = fechaFinalizado.format(outputDft);
            String formattedHoraUTC = horaUTC.format(outputDftHoraUTC);

            lista[nd++] = new DataSismos(id, formattedFechaUtc, formattedHoraUTC, latitud,
                    longitud, profundidad, magnitud, formattedFechaFinalizado);
        }

        if (nd < lista.length) {
            DataSismos[] validDataArray = new DataSismos[nd];
            System.arraycopy(lista, 0, validDataArray, 0, nd);
            lista = validDataArray;
        }

        return lista;
    }

    /**
     * Esta clase permite filtrar por un mes determinado y un año determinado
     * @param filename es el nombre del archivo de donde se subtract la data.
     * @param mes es el mes que sera para filtrar.
     * @param yearUTC es el año que sera para filtrar.
     * @return retorna el valor de la lista.
     * @throws IOException es para manejar las excepciones.
     */
    public static DataSismos[] loadDataSismos(String filename, String mes, int yearUTC) throws IOException {
        String[] lineas = TextUTP.readlinesAsArray(filename, TextUTP.OS.WINDOWS);
        if (lineas.length <= 1) {
            return new DataSismos[0];
        }
        DataSismos[] lista = new DataSismos[lineas.length - 1];
        int nd = 0;

        DateTimeFormatter dftFechaUTC = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dftFechaCorte = DateTimeFormatter.ofPattern("yyyyddMM");
        DateTimeFormatter dftHoraUTC = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter outputDft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputDftHoraUTC = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            String fechaUtcString = cleanString(fragmentos[1]);
            String fechaCorteString = cleanString(fragmentos[7]);
            String horaUtcString = cleanString(fragmentos[2]);

            LocalDate fechaUtc;
            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
            } catch (DateTimeParseException e) {
                System.err.println("Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString);
                continue;
            }

            LocalDate fechaFinalizado;
            try {
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
            } catch (DateTimeParseException e) {
                System.err.println("Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString);
                continue;
            }

            LocalTime horaUTC;
            try {
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (DateTimeParseException e) {
                System.err.println("Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString);
                continue;
            }

            int valorMes = DetectMonth.validationMonth(mes);

            if (yearUTC >= 1960 && (fechaUtc.getYear() != yearUTC || fechaUtc.getMonthValue() != valorMes)) {
                continue;
            }

            long id = Long.parseLong(cleanString(fragmentos[0]));
            double latitud = Double.parseDouble(cleanString(fragmentos[3]));
            double longitud = Double.parseDouble(cleanString(fragmentos[4]));
            int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));
            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

            String formattedFechaUtc = fechaUtc.format(outputDft);
            String formattedFechaFinalizado = fechaFinalizado.format(outputDft);
            String formattedHoraUTC = horaUTC.format(outputDftHoraUTC);

            lista[nd++] = new DataSismos(id, formattedFechaUtc, formattedHoraUTC, latitud,
                    longitud, profundidad, magnitud, formattedFechaFinalizado);
        }

        if (nd < lista.length) {
            DataSismos[] validDataArray = new DataSismos[nd];
            System.arraycopy(lista, 0, validDataArray, 0, nd);
            lista = validDataArray;
        }

        return lista;
    }

    public static DataSismos[] loadDataSismos(String filename, String mes, double magnitudInicial,
                                              double magnitudFinal, int yearUTC) throws IOException {
        String[] lineas = TextUTP.readlinesAsArray(filename, TextUTP.OS.WINDOWS);
        if (lineas.length <= 1) {
            return new DataSismos[0];
        }
        DataSismos[] lista = new DataSismos[lineas.length - 1];
        int nd = 0;

        DateTimeFormatter dftFechaUTC = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dftFechaCorte = DateTimeFormatter.ofPattern("yyyyddMM");
        DateTimeFormatter dftHoraUTC = DateTimeFormatter.ofPattern("HHmmss");
        DateTimeFormatter outputDft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputDftHoraUTC = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            String fechaUtcString = cleanString(fragmentos[1]);
            String fechaCorteString = cleanString(fragmentos[7]);
            String horaUtcString = cleanString(fragmentos[2]);

            LocalDate fechaUtc;
            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
            } catch (DateTimeParseException e) {
                System.err.println("Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString);
                continue;
            }

            LocalDate fechaFinalizado;
            try {
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
            } catch (DateTimeParseException e) {
                System.err.println("Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString);
                continue;
            }

            LocalTime horaUTC;
            try {
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (DateTimeParseException e) {
                System.err.println("Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString);
                continue;
            }

            int valorMes = DetectMonth.validationMonth(mes);

            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

            if (yearUTC >= 1960 && magnitud >= magnitudInicial && magnitud <= magnitudFinal
                    && fechaUtc.getYear() == yearUTC && fechaUtc.getMonthValue() == valorMes) {

                long id = Long.parseLong(cleanString(fragmentos[0]));
                double latitud = Double.parseDouble(cleanString(fragmentos[3]));
                double longitud = Double.parseDouble(cleanString(fragmentos[4]));
                int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));

                String formattedFechaUtc = fechaUtc.format(outputDft);
                String formattedFechaFinalizado = fechaFinalizado.format(outputDft);
                String formattedHoraUTC = horaUTC.format(outputDftHoraUTC);

                lista[nd++] = new DataSismos(id, formattedFechaUtc, formattedHoraUTC, latitud,
                        longitud, profundidad, magnitud, formattedFechaFinalizado);
            }
        }

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
