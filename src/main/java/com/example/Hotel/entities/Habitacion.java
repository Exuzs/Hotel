package com.example.Hotel.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;

    @ManyToOne
    @JoinColumn(name = "tipo_habitacion_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private TipoHabitacion tipoHabitacion;

    private BigDecimal precio;

    @OneToMany(mappedBy = "habitacion")
    private List<Reserva> reservas;

    public Habitacion() {
    }

    public Habitacion(int numero, TipoHabitacion tipoHabitacion, BigDecimal precio) {
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
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
            LocalDate reservaFin = reservaInicio.plusDays(1); // Suponiendo que las reservas son por día
            if ((fechaInicio.isEqual(reservaInicio) || fechaInicio.isAfter(reservaInicio)) &&
                    (fechaInicio.isBefore(reservaFin) || fechaInicio.isEqual(reservaFin)) &&
                    (fechaFin.isAfter(reservaInicio) || fechaFin.isEqual(reservaInicio))) {
                return false;
            }
        }
        return true;
    }
}


