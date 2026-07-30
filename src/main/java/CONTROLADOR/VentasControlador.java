package CONTROLADOR;

import DAO.VentasDAO;
import MODELO.Venta;
import MODELO.Detalle_Venta;
import MODELO.Clientes;
import MODELO.Celular;
import VISTA.Validaciones;
import Utiles.Reportes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class VentasControlador {

    VentasDAO dao = new VentasDAO();
    ClienteControlador clienteControlador = new ClienteControlador();
    CelularControlador celularControlador = new CelularControlador();
    Validaciones v = new Validaciones();

    private static final double IVA = 0.19;

   public void registrar() {

    int idCliente = v.validarEntero("Ingrese el id del cliente: ");
    Clientes cliente = clienteControlador.buscarPorId(idCliente);

    if (cliente == null) {
        System.out.println("No existe un cliente con ese id");
        return;
    }

    int idCelular = v.validarEntero("Ingrese el id del celular: ");
    Celular celular = celularControlador.buscarPorId(idCelular);

    if (celular == null) {
        System.out.println("No existe un celular con ese id");
        return;
    }

    int cantidad = v.validarEntero("Ingrese la cantidad: ");

    if (cantidad > celular.getStock()) {
        System.out.println("No hay stock suficiente de " + celular.getModelo());
        return;
    }

    Detalle_Venta detalle = new Detalle_Venta(0, null, celular, cantidad, celular.getPrecio());

    ArrayList<Detalle_Venta> detalles = new ArrayList<>();
    detalles.add(detalle);

    double subtotal = detalle.getSubtotal();
    double total = subtotal + (subtotal * IVA);

    Venta venta = new Venta(0, cliente, LocalDate.now(), total);
    dao.registrar(venta, detalles);
}

    public void listar() {
        ArrayList<Venta> lista = dao.listar();
        for (Venta v : lista) {
            System.out.println(v);
        }
    }
    
    public void detalle(int idVenta) {
    ArrayList<Detalle_Venta> detalles = dao.listarDetalle(idVenta);

    if (detalles.isEmpty()) {
        System.out.println("No hay detalle para esa venta (o no existe)");
        return;
    }

    for (Detalle_Venta d : detalles) {
        System.out.println(d);
    }
    }

    public void generarReporte() {
        ArrayList<Venta> todasLasVentas = dao.listar();
        Reportes.generarReporteVentas(todasLasVentas);
    }
    
    
    // Agregar dentro de VentasControlador

public void topCelulares() {
    ArrayList<Venta> ventas = dao.listar();
    Map<String, Integer> conteo = ventas.stream()
            .flatMap(venta -> dao.listarDetalle(venta.getId()).stream())
            .collect(Collectors.groupingBy(
                    d -> d.getCelular().getModelo(),
                    Collectors.summingInt(Detalle_Venta::getCantidad)
            ));

    conteo.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " unidades vendidas"));
}

    public void ventasPorMes() {
    ArrayList<Venta> ventas = dao.listar();

    Map<String, Double> totalesPorMes = ventas.stream()
            .collect(Collectors.groupingBy(
                    v -> v.getFecha().getYear() + "-" + String.format("%02d", v.getFecha().getMonthValue()),
                    Collectors.summingDouble(Venta::getTotal)
            ));

    totalesPorMes.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(e -> System.out.println(e.getKey() + ": $" + e.getValue()));
}
}