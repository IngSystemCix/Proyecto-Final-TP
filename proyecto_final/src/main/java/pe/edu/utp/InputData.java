package pe.edu.utp;

import pe.edu.utp.util.TextUTP;

import java.io.IOException;

/**
 * Esta clase me permite separar las lineas de datos del CSV
 * @author Juan Bladimir Romero Collazos
 */

public class InputData {
    /**
     * Nos permite Cargar los datos desde un archivo externo.
     * @param filename aqui va el nombre del archivo CSV de donde sacaremos los datos.
     * @return La lista de datos.
     * @throws IOException Esto me permite controlar los excepciones.
     */
    public static ValidationData [] loadData(String filename) throws IOException{
        // Cargar datos en array String (Autor: Juan Bladimir Romero Collazos)
        String [] lineas = TextUTP.readlinesAsArray(filename);
        ValidationData [] lista = new ValidationData[lineas.length - 1];
        int nd = 0;
        // Iterar todas las líneas (Autor: Juan Bladimir Romero Collazos)
        for (int i = 1; i < lineas.length; i++) {
            // Usamos el split para fragmentar las líneas en 7 elementos (Autor: Juan Bladimir Romero Collazos)
            String linea = lineas[i];
            String [] fragmentos = linea.split(",");
            long id = Long.parseLong(fragmentos[0].replaceAll("\"", "").trim());
            String fechaUTC = fragmentos[1].replaceAll("\"", "").trim();
            String horaUTC = fragmentos[2].replaceAll("\"", "").trim();
            double latitud = Double.parseDouble(fragmentos[3].replaceAll("\"", "").trim());
            double longitud = Double.parseDouble(fragmentos[4].replaceAll("\"", "").trim());
            int profundidad = (int) Double.parseDouble(fragmentos[5].replaceAll("\"", "").trim());
            double magnitud = Double.parseDouble(fragmentos[6].replaceAll("\"", "").trim());
            String fechaCorte = fragmentos[7].replaceAll("\"", "").trim();
            lista[nd++] = new ValidationData(id, fechaUTC, horaUTC, latitud, longitud,
                    profundidad, magnitud, fechaCorte);
        }
        return lista;
    }
}
