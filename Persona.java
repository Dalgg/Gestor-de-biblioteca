package gestorBiblioteca;

public class Persona {
    protected int id;
    protected String contrasenia;
    protected String telefono;
    protected String direccion;
    protected String nombre;

    /**
     * Constructor para asignar valores a los atributos
     * @param id identificador de la persona
     * @param nombre nombre de la persona
     * @param contrasenia contraseña de la persona
     * @param direccion direccion de la persona
     * @param telefono numero de telefono
     */
    public Persona(int id, String nombre, String contrasenia, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    /**
     * Metodo para obtener el nombre de la persona
     * @return el nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo para obtener el id de la persona
     * @return id asignado
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo para imprimir la información de la persona
     */
    public void printInfo(){
        System.out.println("Nombre:"+nombre+" || ID:"+id+" || Numero telefonico:"+telefono+" || Direccion:"+direccion);
    }
}
