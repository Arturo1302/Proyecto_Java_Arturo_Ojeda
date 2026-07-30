package Utiles;

import java.io.FileWriter;
import java.io.IOException;

public class Archivo {

    public static void guardar(String nombreArchivo, String contenido) {
        // try-with-resources: el FileWriter se cierra solo, incluso si hay error
        try (FileWriter fw = new FileWriter(nombreArchivo)) {
            fw.write(contenido);
            System.out.println("Archivo " + nombreArchivo + " generado correctamente!");
        } catch (IOException e) {
            // IOException: el tipo de error para operaciones de archivos (no SQLException)
            System.out.println("Error al generar archivo: " + e.getMessage());
        }
    }
}