package CONTROLADOR;

import DAO.MarcaDAO;
import MODELO.Marca;
import java.util.ArrayList;

public class MarcaControlador {

    MarcaDAO dao = new MarcaDAO();

    public void listar() {
        ArrayList<Marca> lista = dao.listar();
        for (Marca m : lista) {
            System.out.println(m);
        }
    }

    public Marca buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}