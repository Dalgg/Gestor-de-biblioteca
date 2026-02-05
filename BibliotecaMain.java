package gestorBiblioteca;
import java.util.HashMap;
import java.util.Map;
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
        libros.put(2,new Libros(2,"Cujo","Stephen King","Terror",false));
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
                            case "1":
                                int idPrestatario;
                                System.out.println("Ingrese su id de usuario:");
                                idPrestatario=Integer.parseInt(scan.nextLine());
                                if (prestarios.containsKey(idPrestatario)){
                                    String opcionPrestatario;
                                    do {
                                        Help.clearConsole();
                                        Prestatario presta=prestarios.get(idPrestatario);
                                        System.out.println("Bienvenido "+presta.getNombre());
                                        System.out.println("-------------");
                                        System.out.println("DATOS");
                                        System.out.println("-------------");
                                        presta.printInfo();
                                        System.out.println("\n-------------");
                                        System.out.println("FUNCIONES");
                                        System.out.println("-------------");
                                        System.out.println("1- Ver libros reservados");
                                        System.out.println("2- Reservar libros");
                                        System.out.println("3- Salir");
                                        do {
                                            System.out.println("Opcion:");
                                            opcionPrestatario= scan.nextLine();
                                        }while (!IsInteger(opcionPrestatario));
                                        switch (opcionPrestatario){
                                            case "1":
                                                if (presta.listaVacia()){
                                                    System.out.println("Sin libros reservado por el momento");
                                                }
                                                else {
                                                    presta.printHoldRequests();
                                                }
                                                System.out.println("\nPresione Enter para continuar...");
                                                scan.nextLine();
                                                break;
                                            case "2":
                                                if (libros.isEmpty()){
                                                    System.out.println("No hay libros disponibles por el momento");
                                                }
                                                else {
                                                    int eleccionDeLibro;
                                                    System.out.println("Seleccione uno de lo libros disponibles:");
                                                    for (Map.Entry<Integer,Libros> entry: libros.entrySet()){
                                                        System.out.println("Id del libro:"+entry.getKey()+" || Titulo de libro:"+entry.getValue().getTitulo());
                                                    }
                                                    System.out.println("Id del libro:");
                                                    eleccionDeLibro=Integer.parseInt(scan.nextLine());
                                                    if (!libros.containsKey(eleccionDeLibro)){
                                                        System.out.println("Por favor seleccione la id de un libro disponible...");
                                                    }
                                                    else {
                                                        Libros libroReservado=libros.get(eleccionDeLibro);
                                                        if (libroReservado.isPrestado()||libroReservado.isReservado()){
                                                            System.out.println("Lo sentimos ese libro no esta disponible");
                                                            System.out.println("Presione Enter para continuar...");
                                                            scan.nextLine();
                                                        }else{
                                                            libroReservado.makeHoldRequest(presta,libroReservado);
                                                            System.out.println("Presione Enter para continuar...");
                                                            scan.nextLine();
                                                        }
                                                    }
                                                }
                                                break;
                                            case "3":
                                                System.out.println("Saliendo...");
                                                System.out.println("Pulse enter para continuar...");
                                                scan.nextLine();
                                                break;
                                            default:
                                                System.out.println("Ingreser una de las opciones");
                                                break;
                                        }
                                    }while (!Objects.equals(opcionPrestatario,"3"));
                                }
                                break;
                            case "2":
                                int idStaff;
                                System.out.println("Ingrese su id de usuario:");
                                idStaff=Integer.parseInt(scan.nextLine());
                                if (staffs.containsKey(idStaff)){
                                    String opc;
                                    do {
                                        Help.clearConsole();
                                        scan.nextLine();
                                        Staff user=staffs.get(idStaff);
                                        System.out.println("Bienvenido "+user.getNombre());
                                        System.out.println("-------------");
                                        System.out.println("DATOS");
                                        System.out.println("-------------");
                                        user.printInfo();
                                        System.out.println("\n-------------");
                                        System.out.println("FUNCIONES");
                                        System.out.println("-------------");
                                        System.out.println("1- Funciones administrativas");
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
                                break;
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
