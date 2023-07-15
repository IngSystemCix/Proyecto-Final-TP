package pe.edu.utp.util;

import pe.edu.utp.DataSismos;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author Juan Bladimir Romero Collazos
 * @version 1.0.0
 */
public class Calculate {

    /**
     * Este método permite calcular el porcentaje.
     * @param total es el total de elementos en el CSV.
     * @param valorNumerico el el valor que se convertira en porcentaje.
     * @return devuelve el valor en String.
     */
    protected static String porcentaje(int total, double valorNumerico) {
        double resultado = (valorNumerico * 100) / total;
        DecimalFormat df = new DecimalFormat("#.##"); // Dos decimales
        return df.format(resultado);
    }

    /**
     * Me permite contar cuantos tipos de eventos sísmicos hay (leve, moderado o devastador).
     * @return retorna el la cantidad por gravedad.
     * @throws IOException controla las excepciones.
     */
    static double[] gravedadSismos() throws IOException {
        String filename = "./src/main/resources/data.csv";
        DataSismos[] lista = IOSismos.loadDataMagnitudes(filename);
        int magnitudLeve = 0;
        int magnitudModerada = 0;
        int magnitudDevastadora = 0;

        for (DataSismos dataSismos : lista) {
            double magnitud = dataSismos.getMagnitud();
            if (magnitud >= 0 && magnitud < 4) {
                magnitudLeve++;
            } else if (magnitud >= 4 && magnitud < 6) {
                magnitudModerada++;
            } else if (magnitud >= 6 && magnitud <= 10) {
                magnitudDevastadora++;
            }
        }

        double[] resultado = new double[3];
        resultado[0] = magnitudLeve;
        resultado[1] = magnitudModerada;
        resultado[2] = magnitudDevastadora;

        return resultado;
    }
}
