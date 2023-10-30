package repositories;

import entities.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Long> {
    TipoHabitacion findByNombre(String nombre);
}

