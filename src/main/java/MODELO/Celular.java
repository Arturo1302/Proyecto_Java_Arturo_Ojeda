
package MODELO;


public class Celular {
    private int id;
    private Marca marca;
    private String modelo;
    private double precio;
    private int stock;
    private String sistema_operativo;
    private Gama gama;

    public Celular(int id, Marca marca, String modelo, double precio, int stock, String sistema_operativo, Gama gama) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.sistema_operativo = sistema_operativo;
        this.gama = gama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }

    @Override
    public String toString() {
        return """
               ID:                  %s
               Marca:               %s           
               Modelo:              %s
               Gama:                %s
               Sistema Operativo:   %s    
               Precio:              %s
               Stock:               %s
               """.formatted(id,marca.getNombre(),modelo,gama,sistema_operativo,precio,stock);
    }

    

  
    
}
