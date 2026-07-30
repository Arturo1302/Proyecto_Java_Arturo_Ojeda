package DAO;

import CONTROLADOR.Conexion;
import MODELO.Venta;
import MODELO.Detalle_Venta;
import MODELO.Clientes;
import MODELO.Marca;
import MODELO.Celular;
import MODELO.Gama;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VentasDAO {

    Conexion c = new Conexion();

    public void registrar(Venta venta, ArrayList<Detalle_Venta> detalles) {
        try (Connection con = c.conectar()) {

            // 1. Insertamos la venta (cliente, fecha, total)
            String sqlVenta = "insert into ventas(id_cliente, fecha, total) values (?,?,?)";
            PreparedStatement psVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            psVenta.setInt(1, venta.getCliente().getId());
            psVenta.setDate(2, java.sql.Date.valueOf(venta.getFecha()));
            psVenta.setDouble(3, venta.getTotal());
            psVenta.executeUpdate();

            // 2. Averiguamos qué id le puso MySQL a esta venta, porque lo necesitamos
            //    para guardar cada celular vendido en la tabla detalle_ventas
            ResultSet rs = psVenta.getGeneratedKeys();
            int idVenta = 0;
            if (rs.next()) {
                idVenta = rs.getInt(1);
            }

            // 3. Por cada celular que se vendió, guardamos su detalle y bajamos el stock
            for (Detalle_Venta detalle : detalles) {

                String sqlDetalle = "insert into detalle_ventas(id_venta, id_celular, cantidad, precio_unitario) values (?,?,?,?)";
                PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
                psDetalle.setInt(1, idVenta);
                psDetalle.setInt(2, detalle.getCelular().getId());
                psDetalle.setInt(3, detalle.getCantidad());
                psDetalle.setDouble(4, detalle.getPrecioUnitario());
                psDetalle.executeUpdate();

                String sqlStock = "update celulares set stock = stock - ? where id = ?";
                PreparedStatement psStock = con.prepareStatement(sqlStock);
                psStock.setInt(1, detalle.getCantidad());
                psStock.setInt(2, detalle.getCelular().getId());
                psStock.executeUpdate();
            }

            System.out.println("Venta registrada correctamente!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Venta> listar() {
        ArrayList<Venta> respuesta = new ArrayList<>();

        try (Connection con = c.conectar()) {
            String sql = "select v.id, v.fecha, v.total, "
                       + "cl.id as cliente_id, cl.nombre, cl.identificacion, cl.correo, cl.telefono "
                       + "from ventas v "
                       + "inner join clientes cl on v.id_cliente = cl.id";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Clientes cliente = new Clientes(
                        rs.getInt("cliente_id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );

                Venta venta = new Venta(
                        rs.getInt("id"),
                        cliente,
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("total")
                );

                respuesta.add(venta);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return respuesta;
    }
    
    public ArrayList<Detalle_Venta> listarDetalle(int idVenta) {
    ArrayList<Detalle_Venta> lista = new ArrayList<>();

    try (Connection con = c.conectar()) {
        String sql = "select d.id, d.cantidad, d.precio_unitario, "
                   + "c.id as celular_id, c.modelo, c.precio, c.stock, c.sistema_operativo, c.gama, "
                   + "m.id as marca_id, m.nombre as marca_nombre "
                   + "from detalle_ventas d "
                   + "inner join celulares c on d.id_celular = c.id "
                   + "inner join marcas m on c.id_marca = m.id "
                   + "where d.id_venta = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idVenta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Marca marca = new Marca(rs.getInt("marca_id"), rs.getString("marca_nombre"));

            Celular celular = new Celular(
                    rs.getInt("celular_id"), marca, rs.getString("modelo"),
                    rs.getDouble("precio"), rs.getInt("stock"),
                    rs.getString("sistema_operativo"),
                    Gama.valueOf(rs.getString("gama").toLowerCase())
            );

            Detalle_Venta detalle = new Detalle_Venta(
                    rs.getInt("id"), null, celular,
                    rs.getInt("cantidad"), rs.getDouble("precio_unitario")
            );

            lista.add(detalle);
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    return lista;
}
}