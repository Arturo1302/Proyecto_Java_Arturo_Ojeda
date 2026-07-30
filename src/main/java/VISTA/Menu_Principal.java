
package VISTA;


public class Menu_Principal {
    
    public void Menu(){
        Validaciones v = new Validaciones();
        int op ;
        do {
            op = v.validarEnteroRango("""
                                      ====== TECNO STORE ========
                                      |1. Celulares             |
                                      |2. Clientes              |
                                      |3. Ventas                | 
                                      |4. Reportes              |
                                      |5. Salir                 |
                                      ===========================
                                      """, 1, 5);
            switch (op){
                case 1 :
                    Menu_celular m = new Menu_celular();
                    m.Menu();
                    break;
                    
                case 2 :
                    Menu_cliente mc = new Menu_cliente();
                    mc.Menu();
                    break;
                    
                case 3 :
                    Menu_Ventas mv = new Menu_Ventas();
                    mv.Menu();
                    break;    
                case 4 :    
                    Menu_Reportes mr = new Menu_Reportes();
                    mr.Menu();
                    break;
                case 5 :
                    System.out.println("Gracias por utilizar nuestra aplicacion");
                    break;
            }
        }while (op != 5);
    }
}
