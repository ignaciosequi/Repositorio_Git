

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("¡Hola Mundo, soy Ignacio!");
        Persona persona = new Persona("Ignacio", 30);
        persona.mostrarInformacion();
        persona.setEdad(31);
        persona.setNombre("Juan");
        persona.mostrarInformacion();
        persona.saludar();
        persona.cumplirAnios();
        vehiculo miVehiculo = new vehiculo("Toyota", "Corolla", 2020);
        miVehiculo.mostrarInformacion();
        coche miCoche = new coche("Honda", "Civic", 2021);
        miCoche.mostrarInformacion();
        bicicleta miBicicleta = new bicicleta("Giant", "Escape 3", 2020, 150);
        miBicicleta.mostrarInformacion();
        DivisionControlada.main(null);
    }

}
