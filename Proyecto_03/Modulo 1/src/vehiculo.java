public class vehiculo {
    private String marca;
    private String modelo;
    private int anio;

    public vehiculo(String marca, String modelo, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
   public void setAnio(int anio) {
       this.anio = anio;
   }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }
    public String getMarca() {
        return marca;
    }
    public void mostrarInformacion() {
        System.out.println("Marca: " + marca + ", Modelo: " + modelo + ", Año: " + anio);
    }
}


