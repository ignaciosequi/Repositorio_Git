public class Persona {
    private String nombre;
    private int edad;
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public int getEdad() {
        return edad;
    }public String getNombre() {
        return nombre;
    }
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + ", Edad: " + edad);
    }
    public void saludar() {
        System.out.println("¡Hola, " + nombre + "!");
    }
    public void cumplirAnios() {
        edad++;
        System.out.println(nombre + " ha cumplido " + edad + " años.");
    }
    public boolean esMayorDeEdad() {
    return edad >= 18;
}
}