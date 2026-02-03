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
    public static void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.println("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e){
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
public class BibliotecaMain {
    static void main(String[] args) {
        HashMap<Integer, Libros> libros = new HashMap<Integer, Libros>();
        HashMap<Integer, Prestatario> prestarios = new HashMap<Integer, Prestatario>();
        HashMap<Integer,Staff> staffs=new HashMap<>();
        String opcion;
        Scanner scan = new Scanner(System.in);
        libros.put(1, new Libros(1,"1984","Orwell","Novela",false));
        prestarios.put(1, new Prestatario(1,"Diego","123","Casa","5512345678"));
        staffs.put(1, new Staff(1,"Admin","admin","Biblioteca","000",10000.101));
        do {
            Help.clearConsole();
            System.out.println("_____________________________________________________");
            System.out.println("Bienvenido al sistemsa de gestion de Mooney's");
            System.out.println("_____________________________________________________");
            System.out.println("Por favor seleccione una de las siguientes opciones:");
            System.out.println("1. Inciar sesion");
            System.out.println("2. Salir");
            System.out.println("_____________________________________________________");
            do {
                System.out.println("Opcion:");
                opcion=scan.nextLine();
            }while (!IsInteger(opcion));
            switch (opcion){
                case "1":
                    String rol;
                    do {
                        Help.clearConsole();
                        System.out.println("-------------");
                        System.out.println("Rol asignado");
                        System.out.println("-------------");
                        System.out.println("1. Prestatario");
                        System.out.println("2. Staff");
                        System.out.println("3. Regresar");
                        do {
                            System.out.println("Opcion:");
                            rol = scan.nextLine();
                        } while (!IsInteger(rol));
                        Help.clearConsole();
                        switch (rol) {
                            case "2":
                                int idStaff;
                                System.out.println("Ingrese su id de usuario:");
                                idStaff= scan.nextInt();
                                if (staffs.containsKey(idStaff)){
                                    String opc;
                                    do {
                                        scan.nextLine();
                                        Staff user=staffs.get(idStaff);
                                        System.out.println("-------------");
                                        System.out.println("DATOS");
                                        System.out.println("-------------");
                                        System.out.println("Id de usurio:"+user.getId());
                                        System.out.println("Nombre:"+user.getNombre());
                                        System.out.println("\n-------------");
                                        System.out.println("FUNCIONES");
                                        System.out.println("-------------");
                                        System.out.println("1- Funciones administratvias");
                                        System.out.println("2- Salir");
                                        do {
                                            System.out.println("Opcion:");
                                            opc=scan.nextLine();
                                        }while (!IsInteger(opc));
                                        switch (opc){
                                            case "2":
                                                System.out.println();
                                                break;
                                            default:
                                                System.out.println("Ingreser una de las opciones ");
                                                break;
                                        }
                                    }while (!Objects.equals(opc,"2"));
                                }
                                else {
                                    System.out.println("Id no encontrada");
                                }
                            case "3":
                                System.out.println("Regresando...\n");

                                break;
                            default:
                                System.out.println("Seleccione una de las opciones");
                                break;
                        }
                    } while(!Objects.equals(rol,"3"));
                    break;

                case "2":
                    System.out.println("Gracias vuelva pronto.");
                    break;
                default:
                    System.out.println("Seleccione una de las opciones");
                    break;
            }
        } while (!Objects.equals(opcion, "2"));
    }
}
