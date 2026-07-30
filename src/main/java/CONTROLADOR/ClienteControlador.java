package CONTROLADOR;

import DAO.ClientesDAO;
import MODELO.Clientes;
import VISTA.Validaciones;
import java.util.ArrayList;

public class ClienteControlador {

    ClientesDAO dao = new ClientesDAO();
    Validaciones v = new Validaciones();

    public void registrar(Clientes cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            System.out.println("El nombre no puede estar vacío");
            return;
        }
        if (cliente.getIdentificaion() == null || cliente.getIdentificaion().isBlank()) {
            System.out.println("La identificación no puede estar vacía");
            return;
        }
        if (cliente.getCorreo() == null || !cliente.getCorreo().contains("@")) {
            System.out.println("El correo no es válido");
            return;
        }
        dao.registrar(cliente);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }

    // Antes recibía un Clientes completo; ahora recibe solo el id
    public void actualizar(int id) {
        Clientes actual = dao.buscarPorId(id);

        if (actual == null) {
            System.out.println("No existe un cliente con ese id");
            return;
        }

        System.out.println("Datos actuales: " + actual);

        String nombre = v.validarTexto("Ingrese el nuevo nombre: ");
        String identificacion = v.validarTexto("Ingrese la nueva identificación: ");
        String correo = v.validarTexto("Ingrese el nuevo correo: ");
        String telefono = v.validarTexto("Ingrese el nuevo teléfono: ");

        Clientes actualizado = new Clientes(id, nombre, identificacion, correo, telefono);
        dao.actualizar(actualizado);
    }

    // Antes retornaba la lista; ahora la imprime directamente
    public void listar() {
        ArrayList<Clientes> lista = dao.listar();
        for (Clientes c : lista) {
            System.out.println(c);
        }
    }

    public Clientes buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}