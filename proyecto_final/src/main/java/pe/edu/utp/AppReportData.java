package pe.edu.utp;
import java.io.IOException;

public class AppReportData {
    public static void main(String[] args) throws IOException{
        // Este código nos permite visualizar los datos (Autor: Juan Bladimir Romero Collazos)
        String fileName = "./src/main/resources/data.csv";
        ValidationData [] lista = InputData.loadData(fileName);
        for (ValidationData validate : lista) {
            System.out.println(validate);
        }
    }
}
