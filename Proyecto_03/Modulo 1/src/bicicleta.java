public class bicicleta extends vehiculo {
    private int kilometros;
    public bicicleta(String marca, String modelo, int anio, int kilometros) {
        super(marca, modelo, anio);
        this.kilometros = kilometros;
    }
    public int getKilometros() {
        return kilometros;
    }
    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }
    @Override
    public void mostrarInformacion() {
        System.out.println("Soy una bicicleta " + super.getMarca() + " y he recorrido " + kilometros + " kilómetros.");
    }
}
