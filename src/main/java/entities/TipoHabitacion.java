package entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class TipoHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static final String PREMIUM = "Premium";
    public static final String ESTANDAR = "Estándar";

    private String nombre;

    @OneToMany(mappedBy = "tipoHabitacion")
    private List<Habitacion> habitaciones;

    public TipoHabitacion() {
    }

    public TipoHabitacion(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}

