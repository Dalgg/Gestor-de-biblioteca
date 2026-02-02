package gestorBiblioteca;

public class Librarian extends Staff{
    private int officeNo;

    public Librarian(int id, String nombre, String contrasenia, String direccion, String telefono, double salario, int officeNo) {
        super(id, nombre, contrasenia, direccion, telefono, salario);
        this.officeNo = officeNo;
    }

    public int getOfficeNo() {
        return officeNo;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println(officeNo);
    }
}
