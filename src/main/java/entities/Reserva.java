package entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaReserva;
    private String numeroReserva;
    private BigDecimal totalAPagar;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Reserva() {
    }

    public Reserva(LocalDate fechaReserva, String numeroReserva, BigDecimal totalAPagar) {
        this.fechaReserva = fechaReserva;
        this.numeroReserva = numeroReserva;
        this.totalAPagar = totalAPagar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(String numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

