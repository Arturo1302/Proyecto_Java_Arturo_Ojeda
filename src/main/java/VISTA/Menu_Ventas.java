package VISTA;

import CONTROLADOR.VentasControlador;

public class Menu_Ventas {

    public void Menu() {
        int op;
        do {
            Validaciones v = new Validaciones();
            VentasControlador vc = new VentasControlador();
            op = v.validarEnteroRango("""
                                      ======== MENU VENTAS ===========
                                      |1. Registrar Ventas           |                         
                                      |2. Listar ventas              |   
                                      |3. Detalle de venta           |
                                      |4. Salir                      |
                                      ================================
                                      """, 1, 4);
            switch (op) {
                case 1:
                    vc.registrar();
                    break;

                case 2:
                    vc.listar();
                    break;

                case 3:
                    int idVenta = v.validarEntero("Ingrese el id de la venta: ");
                    vc.detalle(idVenta);
                    break;

            }
        } while (op != 4);
    }
}