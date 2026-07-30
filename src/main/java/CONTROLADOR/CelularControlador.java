package CONTROLADOR;

import DAO.CelularDAO;
import MODELO.Celular;
import MODELO.Marca;
import MODELO.Gama;
import VISTA.Validaciones;
import java.util.ArrayList;

public class CelularControlador {

    CelularDAO dao = new CelularDAO();
    Validaciones v = new Validaciones();

    public void registrar(Celular celular) {
        if (celular.getModelo() == null || celular.getModelo().isBlank()) {
            System.out.println("El modelo no puede estar vacío");
            return;
        }
        if (celular.getPrecio() <= 0) {
            System.out.println("El precio debe ser mayor a 0");
            return;
        }
        if (celular.getStock() < 0) {
            System.out.println("El stock no puede ser negativo");
            return;
        }
        dao.registrar(celular);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }

    // Antes recibía un Celular completo; ahora recibe solo el id, y el mismo
    // método se encarga de buscar, mostrar y pedir los datos nuevos
    public void actualizar(int id) {
        Celular actual = dao.buscarPorId(id);

        if (actual == null) {
            System.out.println("No existe un celular con ese id");
            return;
        }

        System.out.println("Datos actuales: " + actual);

        MarcaControlador mc = new MarcaControlador();
        mc.listar();

        int idMarca = v.validarEntero("Ingrese el nuevo id de marca: ");
        Marca marca = mc.buscarPorId(idMarca);

        if (marca == null) {
            System.out.println("No existe una marca con ese id");
            return;
        }

        String modelo = v.validarTexto("Ingrese el nuevo modelo: ");
        double precio = v.validarDecimal("Ingrese el nuevo precio: ");
        int stock = v.validarEntero("Ingrese el nuevo stock: ");
        String so = v.validarTexto("Ingrese el nuevo sistema operativo: ");
        Gama gama = v.validarGama();

        Celular actualizado = new Celular(id, marca, modelo, precio, stock, so, gama);
        dao.actualizar(actualizado);
    }

    // Antes retornaba la lista; ahora la imprime directamente
    public void listar() {
        ArrayList<Celular> lista = dao.listar();
        for (Celular c : lista) {
            System.out.println(c);
        }
    }

    public Celular buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}