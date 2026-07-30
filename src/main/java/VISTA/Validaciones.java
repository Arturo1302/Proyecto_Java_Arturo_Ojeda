package VISTA;

import java.util.Scanner;
import MODELO.Gama;

public class Validaciones {

    public int validarEntero(String mensaje) {
        int dato = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println(mensaje);
                dato = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Error de ingreso de datos");
                sc.nextLine();
            }
        } while (dato < 1);
        return dato;
    }

    public double validarDecimal(String mensaje) {
        double dato = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println(mensaje);
                dato = sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Error de ingreso de datos");
                sc.nextLine();
            }
        } while (dato < 1);
        return dato;
    }

    public int validarEnteroRango(String mensaje, int minimo, int maximo) {
        int dato = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println(mensaje);
                dato = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Error de ingreso de datos");
                sc.nextLine();
            }
        } while (dato < minimo || dato > maximo);
        return dato;
    }

    public String validarTexto(String mensaje) {
        String dato = "";
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println(mensaje);
                dato = sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error de ingreso de datos");
                sc.nextLine();
            }
        } while (dato == null || dato.isBlank());
        return dato;
    }

    public Gama validarGama() {
        int op = validarEnteroRango("""
                                    1. Baja
                                    2. Media
                                    3. Alta
                                    Seleccione la gama:
                                    """, 1, 3);
        return switch (op) {
            case 1 -> Gama.baja;
            case 2 -> Gama.media;
            default -> Gama.alta;
        };
    }
}