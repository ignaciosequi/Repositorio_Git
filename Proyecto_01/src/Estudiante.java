public class Estudiante extends Persona {
    private String carrera;

    public Estudiante (String nombre, int edad, String carrera) {
        super(nombre, edad);
        this.carrera = carrera;
    }
public String getCarrera() {
        return carrera;
    }

    @Override
    public void presentarse() {
        System.out.println("Hola, soy el estudiante " + nombre + ", tengo " + edad + " años y estudio " + carrera + ".");
    }
}