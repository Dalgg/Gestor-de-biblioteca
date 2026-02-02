package gestorBiblioteca;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.LinkedList;

public class Libros {
    private final int idLibro;
    private String titulo;
    private String autor;
    private String genero;
    private boolean prestado;
    private LinkedList<HoldRequest> reservas=new LinkedList<>();

    /**
     * Constructor para la asignación de los parametros del libro
     * @param idLibro identificador unico del libro
     * @param titulo titulo del libro
     * @param autor autor del libro
     * @param genero genero del libro
     * @param prestado confirmación de que esté prestado
     */
    public Libros(int idLibro, String titulo, String autor, String genero, boolean prestado) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.prestado = prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public boolean isPrestado() {
        return prestado;
    }

    // Metodos para libros

    /**
     * Metodo para imprimir la información del libro
     */
    public void printInfo(){
        System.out.println("Id del libro:"+idLibro+"\nTitulo:"+titulo+"\nAutor:"+autor+"\nGenero:"+genero);
    }

    /**
     * Metodo para cambiar la informacion de un libro
     * @param titulo titulo del libro
     * @param autor autor del libro
     * @param genero genero del libro
     */
    public void cambiarInfoLibro(String titulo, String autor, String genero){
        this.titulo=titulo;
        this.autor=autor;
        this.genero=genero;
    }

    public String getTitulo() {
        return titulo;
    }

    /**
     * Este metodo agrega una nueva reserva de un libro
     * @param reserva es la rerserva que se agregara
     */
    public void addHoldRequest(HoldRequest reserva){
        reservas.add(reserva);
    }

    /**
     * Este metodo es el encargado de verificar si un libro puede ser prestado revisando si el libro ya esta reservado
     * @param staff Personal encargado de cambiar el estado de prestamo de un libro
     * @param borrower Persona que solicito el prestamo del libro
     */
    public void issueBook(Staff staff, Prestatario borrower) {
        if (staff == null || borrower == null) {
            throw new IllegalArgumentException("Staff y prestatario no pueden ser nulos");
        }
        if (prestado) {
            System.out.println("El libro buscado ya esta prestado");
            return;
        }
        HoldRequest reserva = null;
        if (!reservas.isEmpty()) {
            reserva = reservas.getFirst();
            if (!reserva.getPrestatario().equals(borrower)){
                System.out.println("Aun no es su turno");
                return;
            }
        }
        this.prestarLibro(borrower,reserva);
        staff.autorizarPrestamo(this);
    }

    /**
     * Metodo encargado de prestar un libro
     * @param borrower prestatario que solicito el libro
     * @param reserva reserva hecha por el prestatario
     */
    private void prestarLibro(Prestatario borrower, HoldRequest reserva){
        LocalDate hoy=LocalDate.now();
        LocalDate regreso=hoy.plusDays(7);
        Prestamo pres=new Prestamo(hoy,regreso,false);
        borrower.agregarLibro(this);
        borrower.agregarPrestamo(pres);
        if (reserva!=null){
            reservas.remove(reserva);
            borrower.removeHoldRequest(reserva);
        }
        System.out.println("Libro prestado a:"+borrower.getNombre());
        prestado=true;
    }
    /**
     * Este metodo crea una reserva tomando como parametro el prestatario que hizo la peticion
     * @param prestatario Persona que hace la rerserva
     */
    public void makeHoldRequest(Prestatario prestatario) {
        if (prestado) {
            System.out.println("Lo siento el libro ya esta prestado, intente mas tarde");
            return;
        }
        HoldRequest reserva = new HoldRequest(prestatario, this);
        if (prestatario.existeReserva(reserva)) {
            System.out.println("Lo siento usted ya reservo este libro antes");
            return;
        }
        Libros libro = this;
        libro.addHoldRequest(reserva);
        prestatario.addHoldRequest(reserva);
        System.out.println("Libro reservado correctamente");
    }

    /**
     * Este metodo imprime todas las reservas que tiene este libro
     */
    public void printHoldRequest(){
        if (reservas.isEmpty()){
            System.out.println("Este libro aun no tiene reservas");
            return;
        }
        else{
            for (int i = 0; i < reservas.size(); i++) {
                HoldRequest reserva=reservas.get(i);
                System.out.println(i+1+"."+reserva.getPrestatario().getNombre());
            }
        }
    }

    /**
     * Metodo para eliminar la primera reserva de un libro
     */
    public void removeHoldRequest(){
        if (reservas.isEmpty()){
            System.out.println("No hay reservas aun para este libro");
            return;
        }
        else{
            HoldRequest reserva=reservas.getFirst();
            Prestatario cliente=reserva.getPrestatario();
            cliente.removeHoldRequest(reserva);
            reservas.remove(reserva);
            System.out.println("Reserva eliminiada correctamente");
        }
    }

    /**
     * Metodo que devuelve al libro para que este disponible
     * @param presta prestatario que devuelve el libro
     * @param prestamo prestamo creado a partir de la reserva
     * @param staff personal que cambia el estado de emicion del libro
     */
    public void returnBook(Prestatario presta, Prestamo prestamo, Staff staff){
        if (!prestado){
            System.out.println("Este libro no esta prestado");
            return;
        }
        if (!presta.existePrestamo(prestamo)){
            System.out.println("El prestamo buscado no existe en su registro");
        }
        double multa=prestamo.computeFine();
        if (multa>0 && !prestamo.isFinePaid()){
            System.out.println("Tiene una multa pendiente de $"+multa);
            return;
        }
        presta.eliminarPrestamo(prestamo);
        staff.cambiarNoEmitido(this);
        System.out.println("El libro devuelto correctamente");
    }

    public void serviceHoldRequest(HoldRequest reserva) {
        if (prestado || reservas.isEmpty()) return;
        if (!reservas.getFirst().equals(reserva)) return;

        prestarLibro(reserva.getPrestatario(), reserva);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libros)) return false;
        Libros libro = (Libros) o;
        return idLibro == libro.idLibro;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idLibro);
    }

}
