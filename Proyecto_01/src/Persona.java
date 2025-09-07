public class Persona {
    protected String nombre;
    protected int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }


    public void presentarse() {
        System.out.println("Hola, soy una persona llamada " + nombre + ", tengo " + edad + " años.");
    }
}