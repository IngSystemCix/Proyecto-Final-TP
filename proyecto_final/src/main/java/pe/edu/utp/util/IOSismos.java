package pe.edu.utp.util;

import pe.edu.utp.DataSismos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Esta clase proporciona funcionalidades para cargar y formatear datos de sismos desde un archivo CSV.
 * También ofrece algunas funcionalidades adicionales.
 * @author Juan Bladimir Romero Collazos, Daniel Ramos Marrufo y Hugo Fupuy Chaname.
 * @version 1.0.1
 */

public class IOSismos {
    /**
     * Este método permite filtrar todos los datos de la data.
     * @param filename nombre del archivo.
     * @return va a retornar la lista con todos los datos.
     * @throws IOException Controla las excepciones.
     */
    public static DataSismos[] loadDataSismos(String filename) throws IOException {
        return loadDataSismos(filename, -1);
    }

    /**
     * Este método nos permite filtrar un año en específico.
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
        boolean continueWithErrors = getConfirmationFromUser();

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            String fechaUtcString = cleanString(fragmentos[1]);
            String fechaCorteString = cleanString(fragmentos[7]);
            String horaUtcString = cleanString(fragmentos[2]);

            LocalDate fechaUtc = null;
            LocalDate fechaFinalizado = null;
            LocalTime horaUTC = null;

            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (DateTimeParseException e) {
                if (continueWithErrors) {
                    System.out.println("Carga de datos cancelada.");
                    break;
                }
                String msgFechaUtc = "Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString;
                String msgFechaFinalizado = "Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString;
                String msgHoraUtc = "Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString;

                GeneratorLog.catchLog(msgFechaUtc, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgFechaFinalizado, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgHoraUtc, GeneratorLog.LEVEL.ERROR);
            }

            if (yearUTC >= 1960 && (fechaUtc != null ? fechaUtc.getYear() : 0) != yearUTC) {
                continue;
            }

            long id = Long.parseLong(cleanString(fragmentos[0]));
            double latitud = Double.parseDouble(cleanString(fragmentos[3]));
            double longitud = Double.parseDouble(cleanString(fragmentos[4]));
            int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));
            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

            String formattedFechaUtc = fechaUtc != null ? fechaUtc.format(outputDft) : null;
            String formattedFechaFinalizado = fechaFinalizado != null ? fechaFinalizado.format(outputDft) : null;
            String formattedHoraUTC = horaUTC != null ? horaUTC.format(outputDftHoraUTC) : null;

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
        boolean continueWithErrors = getConfirmationFromUser();

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            String fechaUtcString = cleanString(fragmentos[1]);
            String fechaCorteString = cleanString(fragmentos[7]);
            String horaUtcString = cleanString(fragmentos[2]);

            LocalDate fechaUtc = null;
            LocalDate fechaFinalizado = null;
            LocalTime horaUTC = null;

            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (DateTimeParseException e) {
                if (continueWithErrors) {
                    System.out.println("Carga de datos cancelada.");
                    break;
                }
                String msgFechaUtc = "Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString;
                String msgFechaFinalizado = "Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString;
                String msgHoraUtc = "Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString;

                GeneratorLog.catchLog(msgFechaUtc, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgFechaFinalizado, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgHoraUtc, GeneratorLog.LEVEL.ERROR);
            }

            if (yearUTCIncial >= 1960 && yearUTCFinal >= yearUTCIncial && ((fechaUtc != null ? fechaUtc.getYear() : 0) < yearUTCIncial
                    || fechaUtc.getYear() > yearUTCFinal)) {
                continue;
            }

            long id = Long.parseLong(cleanString(fragmentos[0]));
            double latitud = Double.parseDouble(cleanString(fragmentos[3]));
            double longitud = Double.parseDouble(cleanString(fragmentos[4]));
            int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));
            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

            String formattedFechaUtc = fechaUtc != null ? fechaUtc.format(outputDft) : null;
            String formattedFechaFinalizado = fechaFinalizado != null ? fechaFinalizado.format(outputDft) : null;
            String formattedHoraUTC = horaUTC != null ? horaUTC.format(outputDftHoraUTC) : null;

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
        boolean continueWithErrors = getConfirmationFromUser();

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            String fechaUtcString = cleanString(fragmentos[1]);
            String fechaCorteString = cleanString(fragmentos[7]);
            String horaUtcString = cleanString(fragmentos[2]);

            LocalDate fechaUtc = null;
            LocalDate fechaFinalizado = null;
            LocalTime horaUTC = null;

            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (DateTimeParseException e) {
                if (continueWithErrors) {
                    System.out.println("Carga de datos cancelada.");
                    break;
                }
                String msgFechaUtc = "Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString;
                String msgFechaFinalizado = "Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString;
                String msgHoraUtc = "Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString;

                GeneratorLog.catchLog(msgFechaUtc, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgFechaFinalizado, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgHoraUtc, GeneratorLog.LEVEL.ERROR);
            }

            int valorMes = DetectMonth.validationMonth(mes);

            if (yearUTC >= 1960 && ((fechaUtc != null ? fechaUtc.getYear() : 0) != yearUTC || fechaUtc.getMonthValue() != valorMes)) {
                continue;
            }

            long id = Long.parseLong(cleanString(fragmentos[0]));
            double latitud = Double.parseDouble(cleanString(fragmentos[3]));
            double longitud = Double.parseDouble(cleanString(fragmentos[4]));
            int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));
            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

            String formattedFechaUtc = fechaUtc != null ? fechaUtc.format(outputDft) : null;
            String formattedFechaFinalizado = fechaFinalizado != null ? fechaFinalizado.format(outputDft) : null;
            String formattedHoraUTC = horaUTC != null ? horaUTC.format(outputDftHoraUTC) : null;

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
     * Este método nos permite filtrar por mes, año y un rango de magnitudes.
     * @param filename el nombre del archivo.
     * @param mes es el mes ej: (enero, febrero, marzo, ...) lo vuelve mayúscula aun que este diferente.
     * @param magnitudInicial Es la referencia inicial de la magnitud.
     * @param magnitudFinal Es la referencia final de la magnitud.
     * @param yearUTC es el año que sucedió el evento sísmico.
     * @return retorna el valor de la lista.
     */
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
        boolean continueWithErrors = getConfirmationFromUser();

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            String fechaUtcString = cleanString(fragmentos[1]);
            String fechaCorteString = cleanString(fragmentos[7]);
            String horaUtcString = cleanString(fragmentos[2]);

            LocalDate fechaUtc = null;
            LocalDate fechaFinalizado = null;
            LocalTime horaUTC = null;

            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (DateTimeParseException e) {
                if (continueWithErrors) {
                    System.out.println("Carga de datos cancelada.");
                    break;
                }
                String msgFechaUtc = "Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString;
                String msgFechaFinalizado = "Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString;
                String msgHoraUtc = "Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString;

                GeneratorLog.catchLog(msgFechaUtc, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgFechaFinalizado, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgHoraUtc, GeneratorLog.LEVEL.ERROR);
            }

            int valorMes = DetectMonth.validationMonth(mes);

            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

            if (yearUTC >= 1960 && magnitud >= magnitudInicial && magnitud <= magnitudFinal
                    && (fechaUtc != null ? fechaUtc.getYear() : 0) == yearUTC && fechaUtc.getMonthValue() == valorMes) {

                long id = Long.parseLong(cleanString(fragmentos[0]));
                double latitud = Double.parseDouble(cleanString(fragmentos[3]));
                double longitud = Double.parseDouble(cleanString(fragmentos[4]));
                int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));

                String formattedFechaUtc = fechaUtc.format(outputDft);
                String formattedFechaFinalizado = fechaFinalizado != null ? fechaFinalizado.format(outputDft) : null;
                String formattedHoraUTC = horaUTC != null ? horaUTC.format(outputDftHoraUTC) : null;

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

    public static DataSismos[] loadDataSismos(String filename,int day ,String mes, int yearUTC) throws IOException {
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
        boolean continueWithErrors = getConfirmationFromUser();

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            String fechaUtcString = cleanString(fragmentos[1]);
            String fechaCorteString = cleanString(fragmentos[7]);
            String horaUtcString = cleanString(fragmentos[2]);

            LocalDate fechaUtc = null;
            LocalDate fechaFinalizado = null;
            LocalTime horaUTC = null;

            try {
                fechaUtc = LocalDate.parse(fechaUtcString, dftFechaUTC);
                fechaFinalizado = LocalDate.parse(fechaCorteString, dftFechaCorte);
                horaUTC = LocalTime.parse(horaUtcString, dftHoraUTC);
            } catch (DateTimeParseException e) {
                if (continueWithErrors) {
                    System.out.println("Carga de datos cancelada...");
                    break;
                }
                String msgFechaUtc = "Error al parsear la fecha UTC en la línea " + (i + 1) + ": " + fechaUtcString;
                String msgFechaFinalizado = "Error al parsear la fecha de corte en la línea " + (i + 1) + ": " + fechaCorteString;
                String msgHoraUtc = "Error al parsear la hora UTC en la línea " + (i + 1) + ": " + horaUtcString;

                GeneratorLog.catchLog(msgFechaUtc, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgFechaFinalizado, GeneratorLog.LEVEL.ERROR);
                GeneratorLog.catchLog(msgHoraUtc, GeneratorLog.LEVEL.ERROR);
            }

            int valorMes = DetectMonth.validationMonth(mes);

            if (31 >= day && day > 0 && yearUTC >= 1960 && (fechaUtc != null ? fechaUtc.getYear() : 0) == yearUTC
                    && fechaUtc.getMonthValue() == valorMes &&fechaUtc.getDayOfMonth() == day) {

                long id = Long.parseLong(cleanString(fragmentos[0]));
                double latitud = Double.parseDouble(cleanString(fragmentos[3]));
                double longitud = Double.parseDouble(cleanString(fragmentos[4]));
                int profundidad = (int) Double.parseDouble(cleanString(fragmentos[5]));
                double magnitud = Double.parseDouble(cleanString(fragmentos[6]));

                String formattedFechaUtc = fechaUtc.format(outputDft);
                String formattedFechaFinalizado = fechaFinalizado != null ? fechaFinalizado.format(outputDft) : null;
                String formattedHoraUTC = horaUTC != null ? horaUTC.format(outputDftHoraUTC) : null;

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

    /**
     * Este método permite que solo filtre magnitudes nada más.
     * @param filename nombre del archivo.
     * @return devuelve la lista de solo magnitudes.
     * @throws IOException Controla las excepciones.
     */
    public static DataSismos[] loadDataMagnitudes(String filename) throws IOException {
        String[] lineas = TextUTP.readlinesAsArray(filename, TextUTP.OS.WINDOWS);
        if (lineas.length <= 1) {
            return new DataSismos[0];
        }
        DataSismos[] lista = new DataSismos[lineas.length - 1];
        int nd = 0;

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i];
            String[] fragmentos = linea.split(",");

            double magnitud = Double.parseDouble(cleanString(fragmentos[6]));
            lista[nd++] = new DataSismos(magnitud);
        }

        if (nd < lista.length) {
            DataSismos[] validDataArray = new DataSismos[nd];
            System.arraycopy(lista, 0, validDataArray, 0, nd);
            lista = validDataArray;
        }

        return lista;
    }

    /**
     * Este método me devuelve un true o false dependiendo lo que elijamos.
     * @return retorta la respuesta ignorando si es mayúscula o minúscula.
     */
    private static boolean getConfirmationFromUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("¿Desea continuar con la carga de datos pese a encontrar errores? (y/n)");
        String response = input.nextLine().toUpperCase();
        if (response.equals("Y")) System.out.println("Se esta generando el reporte de errores...");;
        return !response.equalsIgnoreCase("Y");
    }

    /**
     * Este método hace una limpieza a la cadena de texto.
     * @param input Es la entrada del dato.
     * @return devuelve la cadena ya limpia.
     */
    private static String cleanString(String input) {
        return input.replaceAll("\"", "").trim();
    }
}
