package pe.edu.utp;
import java.io.IOException;

public class AppReporte {
    public static void main(String[] args) throws IOException{
        String fileName = "./src/main/resources/data.csv";
        ValidationData [] lista = InputData.loadData(fileName);
        for (ValidationData validate : lista) {
            System.out.println(validate);
        }
    }
}
