package Utiles;

import MODELO.Venta;
import java.util.ArrayList;

public class Reportes {

    public static void generarReporteVentas(ArrayList<Venta> ventas) {

        // StringBuilder: mejor que concatenar con "+" dentro de un bucle
        StringBuilder contenido = new StringBuilder();

        contenido.append("========================================\n");
        contenido.append("     REPORTE DE VENTAS - TECNOSTORE\n");
        contenido.append("========================================\n\n");

        double totalGeneral = 0;

        for (Venta venta : ventas) {
            // Usamos el toString() que ya tiene Venta (con su formato de texto bonito)
            contenido.append(venta.toString());
            contenido.append("----------------------------------------\n");
            totalGeneral += venta.getTotal();
        }

        contenido.append("\nTOTAL GENERAL DE VENTAS: ").append(totalGeneral).append("\n");

        // Delegamos el "guardar en disco" a Archivo — Reportes solo arma el texto
        Archivo.guardar("reporte_ventas.txt", contenido.toString());
    }
}