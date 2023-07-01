package pe.edu.utp;

import java.io.IOException;

public class AppReporte {
    public static void main(String[] args) throws IOException {
        String fileName = "D:\\CarpetaUniversidadUTP\\UTPciclo 3\\TALLER_DE_PROGRAMACIÓN\\proyecto final recursos\\Proyecto-Final-TP\\proyecto_final\\src\\main\\resources\\data.csv";
        ValidationData [] lista = InputData.loadData(fileName);
        for (ValidationData data : lista) {
            System.out.println(data);
        }
    }
}
