package CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author camper
 */
public class Conexion {
    public Connection conectar(){
        Connection c = null;
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectotecnostore", "root", "arturo1302");
        }catch (SQLException e){
            System.out.println(e.getMessage()); 
        }
        return c;
    }
}