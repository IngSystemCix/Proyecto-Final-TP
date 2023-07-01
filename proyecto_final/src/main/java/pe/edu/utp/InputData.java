package pe.edu.utp;

import pe.edu.utp.util.TextUTP;

import java.io.IOException;

public class InputData {
    public static ValidationData [] loadData(String filename) throws IOException{
        // Cargar datos en array String (Autor: Juan Bladimir Romero Collazos)
        String [] lineas = TextUTP.readlinesAsArray(filename);
        ValidationData [] lista = new ValidationData[lineas.length];
        int nd = 0;
        // Iterar todas las líneas (Autor: Juan Bladimir Romero Collazos)
        for (String linea : lineas) {
            // Usamos el split para fragmentar las líneas en 7 elementos (Autor: Juan Bladimir Romero Collazos)
            String [] fragmentos = linea.split(",");
            long id = Long.parseLong(fragmentos[0]);
            String fechaUTC = fragmentos[1];
            String horaUTC = fragmentos[2];
            double latitud = Double.parseDouble(fragmentos[3]);
            int profundidad = Integer.parseInt(fragmentos[4]);
            double magnitud = Double.parseDouble(fragmentos[5]);
            String fechaCorte = fragmentos[6];
            lista[nd++] = new ValidationData(id, fechaUTC, horaUTC, latitud,
                    profundidad, magnitud, fechaCorte);
        }
        return lista;
    }
}
