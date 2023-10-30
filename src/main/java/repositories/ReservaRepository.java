package repositories;

import entities.Reserva;
import entities.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByClienteId(Long clienteId);
    List<Reserva> findByHabitacionTipoHabitacionAndFechaReservaBetween(TipoHabitacion tipoHabitacion, LocalDate startDate, LocalDate endDate);
}

