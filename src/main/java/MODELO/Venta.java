
package MODELO;

import java.time.LocalDate;


public class Venta {
    
    private int id;
    private Clientes cliente;      
    private LocalDate fecha;
    private double total;

    public Venta(int id, Clientes cliente, LocalDate fecha, double total) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return """
               ID:          %s
               Cliente:     %s
               Fecha:       %s
               Total:       %s
               """.formatted(id,cliente.getNombre(),fecha,total);
    }
}