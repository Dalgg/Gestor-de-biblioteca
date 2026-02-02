package gestorBiblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private LocalDate issueDate;
    private LocalDate dateReturn;
    private boolean finePaid;
    private static final double deudaPorDia=10.0;

    public Prestamo(LocalDate issueDate, LocalDate dateReturn, boolean finePaid) {
        this.issueDate = issueDate;
        this.dateReturn = dateReturn;
        this.finePaid = finePaid;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }

    public boolean isFinePaid() {
        return finePaid;
    }

    public void payFine(){
        finePaid=true;
    }

    public double computeFine(){
        LocalDate hoy=LocalDate.now();
        if (hoy.isAfter(dateReturn)){
            long diasTarde= ChronoUnit.DAYS.between(dateReturn,hoy);
            return diasTarde*deudaPorDia;
        }
        return 0.0;
    }

    public LocalDate renewIssuedBook(LocalDate nuevaFecha){
        return dateReturn=nuevaFecha;
    }
}

