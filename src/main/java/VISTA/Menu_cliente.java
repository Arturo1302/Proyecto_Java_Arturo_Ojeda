package VISTA;

import CONTROLADOR.ClienteControlador;
import MODELO.Clientes;

public class Menu_cliente {

    public void Menu() {
        int op;
        do {
            Validaciones v = new Validaciones();
            ClienteControlador cc = new ClienteControlador();
            op = v.validarEnteroRango("""
                                    ========== MENU CLIENTES =========
                                    |1. Registrar Cliente           |
                                    |2. Actualizar Cliente          |
                                    |3. Eliminar Cliente            |
                                    |4. Listar Cliente              |
                                    |5. Salir                       |
                                    =================================  
                                    """, 1, 5);
            switch (op) {
                case 1:
                    System.out.println("******* REGISTRO CLIENTE *********");
                    String nombre = v.validarTexto("Ingrese el nombre: ");
                    String identificacion = v.validarTexto("Ingrese la identificación: ");
                    String correo = v.validarTexto("Ingrese el correo: ");
                    String telefono = v.validarTexto("Ingrese el teléfono: ");
                    cc.registrar(new Clientes(0, nombre, identificacion, correo, telefono));
                    break;

                case 2:
                    cc.listar();
                    int idActualizar = v.validarEntero("Ingrese el id a actualizar: ");
                    cc.actualizar(idActualizar);
                    break;

                case 3:
                    cc.listar();
                    int idEliminar = v.validarEntero("Ingrese el id a eliminar: ");
                    cc.eliminar(idEliminar);
                    break;

                case 4:
                    cc.listar();
                    break;
            }
        } while (op != 5);
    }
}