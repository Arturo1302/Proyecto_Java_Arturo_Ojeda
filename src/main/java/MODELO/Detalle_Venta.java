
package MODELO;


public class Detalle_Venta {
    
    private int id;
    private Venta venta;
    private Celular celular;
    private int cantidad;
    private double precioUnitario;


    public Detalle_Venta(int id, Venta venta, Celular celular, int cantidad, double precioUnitario) {
        this.id = id;
        this.venta = venta;
        this.celular = celular;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Bonus: método útil de negocio que vive bien aquí (no necesita ir a la BD para calcularse)
    public double getSubtotal() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return """
               ================================
               ------ DETALLE DE VENTA --------
               ================================
               Celular:                     %s
               Cantidad:                    %s
               Precio(Unitario):            %s
               Total:                       %s               
               """.formatted(celular.getModelo(),cantidad,precioUnitario,getSubtotal());
    }

    
}
