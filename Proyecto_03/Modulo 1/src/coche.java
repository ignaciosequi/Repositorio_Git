public class coche extends vehiculo {
   
    public coche(String marca, String modelo, int anio) {
        super(marca, modelo, anio);
    }
    @Override
    public void mostrarInformacion() {
        System.out.println("Soy un coche " + getMarca());
    }
}
