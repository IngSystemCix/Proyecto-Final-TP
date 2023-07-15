package pe.edu.utp.util;

import java.io.File;

/**
 * Esta clase lo que permite eliminar archivos para actualizarlos.
 * @author Juan Bladimir Romero Collazos
 * @version 1.0.1
 */

public class FileDeleter {
    /**
     * Este método permite que borre un archivo
     * @param filename Es el nombre del archivo.
     */
    public static void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("Archivo eliminado exitosamente: " + file.getName());
            } else {
                System.out.println("No se pudo eliminar el archivo: " + file.getName());
            }
        } else {
            System.out.println("El archivo no existe: " + file.getName());
        }
    }
}

