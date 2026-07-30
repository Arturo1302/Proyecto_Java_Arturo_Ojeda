/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VISTA;

import CONTROLADOR.VentasControlador;

/**
 *
 * @author camper
 */
public class Menu_Reportes {
    

        public void Menu() {
        int op;
        do {
            Validaciones v = new Validaciones();
            VentasControlador vc = new VentasControlador();
            op = v.validarEnteroRango("""
                                      ======== MENU REPORTES =========
                                      |1. Generar reporte (txt)      |
                                      |2. Stock Bajos                |
                                      |3. TOP 3 Celulares Vendidos   |
                                      |4. Salir                      |         
                                      ================================
                                      """, 1, 4);
            switch (op) {
                case 1:
                    vc.generarReporte();
                    break;

                case 2:
                    vc.listar();
                    break;

                case 3:
                    
                    break;
            }        
        }while ( op!=4);
        
        }
}
