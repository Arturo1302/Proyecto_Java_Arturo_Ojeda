package DAO;

import CONTROLADOR.Conexion;
import MODELO.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClientesDAO {

    Conexion c = new Conexion();
//=====================================================================================================================================================
    
    public void registrar(Clientes cliente) {
        try (Connection con = c.conectar()) {
            String sql = "insert into clientes(nombre, identificacion, correo, telefono) values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getIdentificaion());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());
            ps.executeUpdate();
            System.out.println("Cliente registrado correctamente!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

 //=====================================================================================================================================================
    
    public void actualizar(Clientes cliente) {
        try (Connection con = c.conectar()) {
            String sql = "update clientes set nombre=?, identificacion=?, correo=?, telefono=? where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getIdentificaion());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getId());
            ps.executeUpdate();
            System.out.println("Cliente actualizado correctamente!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//=====================================================================================================================================================
    
    public ArrayList<Clientes> listar() {
        ArrayList<Clientes> respuesta = new ArrayList<>();
        try (Connection con = c.conectar()) {
            String sql = "select id, nombre, identificacion, correo, telefono from clientes";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                respuesta.add(new Clientes(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

//=====================================================================================================================================================
    
    public void eliminar(int id) {
        int op = JOptionPane.showConfirmDialog(null,
                "¿Está segur@ de eliminar el cliente con id " + id + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (op == 0) {
            try (Connection con = c.conectar()) {
                PreparedStatement ps = con.prepareStatement("delete from clientes where id=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Cliente eliminado con exito!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Operación cancelada!");
        }
    }
//==================================================================================================================================================
    public Clientes buscarPorId(int id) {
    Clientes cliente = null;
    try (Connection con = c.conectar()) {
        PreparedStatement ps = con.prepareStatement("select id, nombre, identificacion, correo, telefono from clientes where id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            cliente = new Clientes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return cliente;
}
}
