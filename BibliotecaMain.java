package gestorBiblioteca;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import static gestorBiblioteca.Help.IsInteger;

class Help {
    public static boolean IsInteger(String text) {
        int v;
        try {
            v=Integer.parseInt(text);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Por favor solo ingrese numeros.");
            return false;
        }
    }
}
public class BibliotecaMain {
    static void main(String[] args) {

        HashMap<Integer, Libros> libros = new HashMap<Integer, Libros>();
        HashMap<Integer, Prestatario> prestarios = new HashMap<Integer, Prestatario>();
        String opcion;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Bienvenido al sistemsa de gestion de Diego´s library");
            System.out.println("_____________________________________________________");
            System.out.println("Por favor seleccione una de las siguientes opciones:");
            System.out.println("1. Inciar sesion");
            System.out.println("2. Funciones administrativas");
            System.out.println("3. Salir");
            System.out.println("_____________________________________________________");
            do {
                System.out.println("Opcion:");
                opcion=scan.nextLine();
            }while (!IsInteger(opcion));
            switch (opcion){
                case "3":
                    System.out.println("Gracias vuelva pronto.");
                    break;
            }
        } while (!Objects.equals(opcion, "3"));
    }
}
