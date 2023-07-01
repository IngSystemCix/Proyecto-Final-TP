package pe.edu.utp;

import java.io.IOException;

public class AppReporte {
    public static void main(String[] args) throws IOException {
        String fileName = "..//..//resouces//data.csv";
        ValidationData [] lista = InputData.loadData(fileName);
        for (ValidationData data : lista) {
            System.out.println(data);
        }
    }
}
