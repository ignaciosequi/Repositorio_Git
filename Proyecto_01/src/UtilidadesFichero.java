import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;




public class UtilidadesFichero {

    // Método que escribe en un fichero
    public static void escribir(String nombreFichero, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero, true))) {
            writer.write(contenido);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error escribiendo en el fichero: " + e.getMessage());
            }
        }
    }
