package gestorBiblioteca;

public class Staff extends Persona{
    private double salario;

    public Staff(int id, String nombre, String contrasenia, String direccion, String telefono, double salario) {
        super(id, nombre, contrasenia, direccion, telefono);
        this.salario = salario;
    }

    public void printInfo(){
        super.printInfo();
        System.out.println("id:"+id);
    }
    public void autorizarPrestamo(Libros libro) {
        if (libro == null) return;
        libro.setPrestado(true);
    }
    public void cambiarNoEmitido(Libros libro){
        libro.setPrestado(false);
    }
}
