package DAO;

import CONTROLADOR.Conexion;
import MODELO.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarcaDAO {

    Conexion c = new Conexion();

    public ArrayList<Marca> listar() {
        ArrayList<Marca> respuesta = new ArrayList<>();
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select id, nombre from marcas order by id ASC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                respuesta.add(new Marca(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Marca buscarPorId(int id) {
        Marca marca = null;
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("select id, nombre from marcas where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                marca = new Marca(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return marca;
    }
}