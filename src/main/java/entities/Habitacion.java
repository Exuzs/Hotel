package entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @Enumerated(EnumType.STRING)
    private TipoHabitacion tipoHabitacion;

    private BigDecimal precioBase;

    @OneToMany(mappedBy = "habitacion")
    private List<Reserva> reservas;

    public Habitacion() {
    }

    public Habitacion(String numero, TipoHabitacion tipoHabitacion, BigDecimal precioBase) {
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.precioBase = precioBase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public boolean estaDisponibleParaFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        for (Reserva reserva : reservas) {
            LocalDate reservaInicio = reserva.getFechaReserva();
            LocalDate reservaFin = reserva.getFechaReserva().plusDays(1); // Suponiendo que las reservas son por día
            if ((fechaInicio.isEqual(reservaInicio) || fechaInicio.isAfter(reservaInicio)) &&
                    (fechaInicio.isBefore(reservaFin) || fechaInicio.isEqual(reservaFin)) &&
                    (fechaFin.isAfter(reservaInicio) || fechaFin.isEqual(reservaInicio))) {
                return false;
            }
        }
        return true;
    }

}

