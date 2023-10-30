package repositories;

import entities.Cliente;
import entities.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByReservasHabitacionTipoHabitacion(TipoHabitacion tipoHabitacion);
}

