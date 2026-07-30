package VISTA;

import CONTROLADOR.CelularControlador;
import CONTROLADOR.MarcaControlador;
import MODELO.Celular;
import MODELO.Marca;
import MODELO.Gama;

public class Menu_celular {

    public void Menu() {
        int op;
        do {
            Validaciones v = new Validaciones();
            CelularControlador cc = new CelularControlador();
            op = v.validarEnteroRango("""
                                    ========== MENU CELULAR =========
                                    |1. Registrar Celular           |
                                    |2. Actualizar Celular          |
                                    |3. Eliminar Celular            |
                                    |4. Listar Celulares            |
                                    |5. Salir                       |
                                    =================================  
                                    """, 1, 5);
            switch (op) {
               case 1:
                    System.out.println("******* REGISTRO CELULAR *********");

                    MarcaControlador mc = new MarcaControlador();
                    mc.listar();

                    int idMarca = v.validarEntero("Ingrese el id de la marca: ");
                    Marca marca = mc.buscarPorId(idMarca);

                    if (marca == null) {
                        System.out.println("No existe una marca con ese id");
                        break;
                    }

                    String modelo = v.validarTexto("Ingrese el modelo: ");
                    double precio = v.validarDecimal("Ingrese el precio: ");
                    int stock = v.validarEntero("Ingrese el stock: ");
                    String so = v.validarTexto("Ingrese el sistema operativo: ");
                    Gama gama = v.validarGama();
                    cc.registrar(new Celular(0, marca, modelo, precio, stock, so, gama));
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