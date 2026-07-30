package VISTA;

import CONTROLADOR.VentasControlador;
import CONTROLADOR.CelularControlador;

public class Menu_Reportes {

    public void Menu() {
        int op;
        do {
            Validaciones v = new Validaciones();
            VentasControlador vc = new VentasControlador();
            CelularControlador cc = new CelularControlador();

            op = v.validarEnteroRango("""
                                      ======== MENU REPORTES =========
                                      |1. Generar reporte (txt)      |
                                      |2. Stock Bajo                 |
                                      |3. TOP 3 Celulares Vendidos   |
                                      |4. Ventas totales por mes     |
                                      |5. Salir                      |         
                                      =================================
                                      """, 1, 5);
            switch (op) {
                case 1:
                    vc.generarReporte();
                    break;
                case 2:
                    cc.stockBajo();
                    break;
                case 3:
                    vc.topCelulares();
                    break;
                case 4:
                    vc.ventasPorMes();
                    break;
            }
        } while (op != 5);
    }
}