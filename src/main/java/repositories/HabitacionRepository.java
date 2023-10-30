package repositories;

import entities.Habitacion;
import entities.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findByTipoHabitacionAndReservasFechaReservaIsNull(TipoHabitacion tipoHabitacion);
    List<Habitacion> findByReservasFechaReservaIsNull();
    List<Habitacion> findByReservasFechaReserva(LocalDate fechaReserva);
}

