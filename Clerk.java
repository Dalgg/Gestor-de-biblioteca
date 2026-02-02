package gestorBiblioteca;

public class Clerk extends Staff{
    private int deskNumber;

    public Clerk(int id, String nombre, String contrasenia, String direccion, String telefono, double salario, int deskNumber) {
        super(id, nombre, contrasenia, direccion, telefono, salario);
        this.deskNumber = deskNumber;
    }

    public int getDeskNumber() {
        return deskNumber;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println(deskNumber);
    }
}
