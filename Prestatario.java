package gestorBiblioteca;

import java.util.ArrayList;

public class Prestatario extends Persona{
    private ArrayList<HoldRequest> lista;
    private ArrayList<Libros> libros;
    private ArrayList<Prestamo> prestamos;
    public Prestatario(int id, String nombre, String contrasenia, String direccion, String telefono) {
        super(id, nombre, contrasenia, direccion, telefono);
        this.lista=new ArrayList<>();
        this.libros=new ArrayList<>();
        this.prestamos=new ArrayList<>();
    }

    /**
     * Metodo para mostrar la informacion del prestatario
     */
    public void printInfo(){
        System.out.println("Nombre:"+nombre+" || ID:"+id+" || Numero telefonico:"+telefono+" || Direccion:"+direccion);
    }

    /**
     * Metodo para imprimir la lista de libros prestados del prestatario
     */
    public void printBorrowedBooks(){
        if (libros.isEmpty()){
            System.out.println("No tiene libros reservados aun");
            return;
        }
        for (int i = 0; i < libros.size(); i++) {
            System.out.println(i+1+". "+libros.get(i).toString()+"\n");
        }
    }

    public void printHoldRequests(){
        if (listaVacia()){
            System.out.println("No tiene reservas agregadas por el momento");
        }
        else{
            int i=1;
            System.out.println("Libros reservados:");
            for (HoldRequest hold: lista){
                System.out.println(i+". "+hold.getLibro().getTitulo());
                i++;
            }
        }
    }
    /**
     * Agrega una nueva reserva a la lista del prestatario
     * @param reserva reserva que se va a agregar
     */
    public void addHoldRequest(HoldRequest reserva){
        lista.add(reserva);
    }

    public void updateName(String nombre){
        if(nombre.isEmpty()){
            System.out.println("El campo no puede estar vacio");
            return;
        }
        else {
            this.nombre=nombre;
        }
    }
    public void updatePassword(String contrasenia){
        if(contrasenia.isEmpty()){
            System.out.println("El campo no puede estar vacio");
            return;
        }
        this.contrasenia=contrasenia;
    }
    public void updateDirection(String direccion){
        if(direccion.isEmpty()){
            System.out.println("El campo no puede estar vacio");
            return;
        }
        this.direccion=direccion;
    }
    public void updatePhone(String telefono){
        if(telefono.isEmpty()){
            System.out.println("El campo no puede estar vacio");
            return;
        }
        if (telefono.length()<10){
            System.out.println("El telefono no puede tener menos de 10 caracteres");
            return;
        }

        this.telefono=telefono;
    }

    /**
     * Metodo para verificar si una lista esta vacia
     * @return el estado de una lista
     */
    public boolean listaVacia(){
        return lista.isEmpty();
    }


    public void removeHoldRequest(HoldRequest reserva) {
        if (lista.remove(reserva)) {
            System.out.println("Reserva eliminada correctamente");
        } else {
            System.out.println("No se encontró la reserva registrada");
        }
    }
    //Metodos extras
    public void agregarLibro(Libros libro){
        libros.add(libro);
    }
    public boolean existeLibro(Libros libro){
        return libros.contains(libro);
    }
    public void agregarPrestamo(Prestamo pres){
        prestamos.add(pres);
    }
    public boolean existePrestamo(Prestamo prestamo){
        return prestamos.contains(prestamo);
    }
    public void eliminarPrestamo(Prestamo pres){
        prestamos.remove(pres);
    }
    public boolean existeReserva(HoldRequest reserva){
        return lista.contains(reserva);
    }
}
