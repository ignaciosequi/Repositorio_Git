import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Persona mmiPersona = new Persona("Juan", 30);
        mmiPersona.presentarse();
        Estudiante miEstudiante = new Estudiante("Ana", 20, "Matemáticas");
        Estudiante miEstudiante2 = new Estudiante("Luis", 22, "Matemáticas");
        miEstudiante.presentarse();
        miEstudiante2.presentarse();
        // Comparar edades
        if (miEstudiante.edad > miEstudiante2.edad) {
            System.out.println("Ana es mayor que Luis.");
        } else if (miEstudiante.edad < miEstudiante2.edad) {
            System.out.println("Luis es mayor que Ana.");
        } else {
            System.out.println("Ana y Luis tienen la misma edad.");
        }
        if (miEstudiante.getCarrera().equals(miEstudiante2.getCarrera())) {
            System.out.println("Ana está estudiando la misma carrera que Luis.");
        } else {
            System.out.println("Ana y luis no están estudiando la misma carrera.");
        }

       

       // Leer personas desde un archivo CSV
        ArrayList<Persona> personas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Personas.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
        
                String[] datos = linea.split(";");
                String nombre = datos[0];
                int edad = Integer.parseInt(datos[1]);
                personas.add(new Persona(nombre, edad + 5000));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            System.out.println("Lectura de archivo finalizada.");
        }
        // Mostrar las personas leídas
        for (int i = 1; i <= 2; i++) {
            System.out.println(i + " VEZ QUE PASA POR FOR:" );
            UtilidadesFichero.escribir("Salida.txt", i + " Vez que pasa");
            for (Persona p : personas) {
                p.presentarse();
                UtilidadesFichero.escribir("Salida.txt", miEstudiante.nombre + "---" + miEstudiante.edad);
            }
        }
        
    }
}
