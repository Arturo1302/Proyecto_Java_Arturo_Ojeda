package DAO;

import CONTROLADOR.Conexion;
import MODELO.Celular;
import MODELO.Marca;
import MODELO.Gama;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CelularDAO {

    Conexion c = new Conexion();

//======================================================================================================================================================
    
    public void registrar(Celular celular) {
        try (Connection con = c.conectar()) {
            String sql = "insert into celulares(id_marca, modelo, precio, stock, sistema_operativo, gama) values (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, celular.getMarca().getId());
            ps.setString(2, celular.getModelo());
            ps.setDouble(3, celular.getPrecio());
            ps.setInt(4, celular.getStock());
            ps.setString(5, celular.getSistema_operativo());
            ps.setString(6, celular.getGama().toString());
            ps.executeUpdate();
            
            System.out.println("Celular registrado correctamente :)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//======================================================================================================================================================
    public void actualizar(Celular celular) {
        try (Connection con = c.conectar()) {
            String sql = "update celulares set id_marca=?, modelo=?, precio=?, stock=?, sistema_operativo=?, gama=? where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, celular.getMarca().getId());
            ps.setString(2, celular.getModelo());
            ps.setDouble(3, celular.getPrecio());
            ps.setInt(4, celular.getStock());
            ps.setString(5, celular.getSistema_operativo());
            ps.setString(6, celular.getGama().toString());
            ps.setInt(7, celular.getId());
            ps.executeUpdate();
            
            System.out.println("Celular actualizado correctamente :)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//======================================================================================================================================================
    
    public ArrayList<Celular> listar() {
        ArrayList<Celular> respuesta = new ArrayList<>();
        try (Connection con = c.conectar()) {
            String sql = "select c.id, c.modelo, c.precio, c.stock, c.sistema_operativo, c.gama, "
                       + "m.id as marca_id, m.nombre as marca_nombre "
                       + "from celulares c "
                       + "inner join marcas m on c.id_marca = m.id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Marca marca = new Marca(rs.getInt("marca_id"), rs.getString("marca_nombre"));
                respuesta.add(new Celular(
                        rs.getInt("id"), marca, rs.getString("modelo"),
                        rs.getDouble("precio"), rs.getInt("stock"),
                        rs.getString("sistema_operativo"),
                        Gama.valueOf(rs.getString("gama").toLowerCase())
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
//======================================================================================================================================================
    
    public void eliminar(int id) {
        int op = JOptionPane.showConfirmDialog(null,"¿Está segur@ de eliminar el celular con id " + id + "?","Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (op == 0) {
            try (Connection con = c.conectar()) {
                PreparedStatement ps = con.prepareStatement("delete from celulares where id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Celular eliminado con exito!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada :(");
        }
    }
//==================================================================================================================================================

    public Celular buscarPorId(int id) {
        Celular celular = null;
        try (Connection con = c.conectar()) {
            String sql = "select c.id, c.modelo, c.precio, c.stock, c.sistema_operativo, c.gama, "
                       + "m.id, m.nombre from celulares c "
                       + "inner join marcas m on c.id_marca = m.id where c.id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Marca marca = new Marca(rs.getInt(7), rs.getString(8));
                celular = new Celular(rs.getInt(1), marca, rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5), Gama.valueOf(rs.getString(6).toLowerCase()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celular;
}    
    
}