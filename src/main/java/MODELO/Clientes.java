
package MODELO;


public class Clientes {
    
    private int id;
    private String nombre;
    private String identificaion;
    private String correo;
    private String telefono;

    public Clientes(int id, String nombre, String identificaion, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.identificaion = identificaion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificaion() {
        return identificaion;
    }

    public void setIdentificaion(String identificaion) {
        this.identificaion = identificaion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return """
               ID:                   %s
               Nombre:               %s
               Identificacion:       %s
               Correo:               %s
               Telefono:             %s
               """.formatted(id,nombre,identificaion,correo,telefono);
    }
    
    
    
}
