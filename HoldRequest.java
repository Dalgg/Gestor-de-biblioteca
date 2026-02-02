package gestorBiblioteca;

import java.time.LocalDate;
import java.util.Objects;

public class HoldRequest {

    private LocalDate requestDate;
    private Prestatario prestatario;
    private Libros libro;

    /**
     * Constructor de la solicitud de reserva
     * @param prestatario persona que pide el libro
     * @param libro libro solicitado
     */
    public HoldRequest(Prestatario prestatario, Libros libro) {
        this.requestDate = LocalDate.now();
        this.prestatario = prestatario;
        this.libro = libro;
    }

    // Getters
    public LocalDate getRequestDate() {
        return requestDate;
    }
    public Prestatario getPrestatario() {
        return prestatario;
    }
    public Libros getLibro() {
        return libro;
    }
    /**
     * Dos reservas son iguales si corresponden
     * al mismo prestatario y al mismo libro
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoldRequest that = (HoldRequest) o;
        return Objects.equals(prestatario, that.prestatario) &&
                Objects.equals(libro, that.libro);
    }
    @Override
    public int hashCode() {
        return Objects.hash(prestatario, libro);
    }
    /**
     * Representación en texto de la reserva
     */
    @Override
    public String toString() {
        return "Reserva {" +
                "fecha=" + requestDate +
                ", prestatario=" + prestatario.getNombre() +
                ", libro=" + libro.getTitulo() +
                '}';
    }
}
