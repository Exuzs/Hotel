package services;

import entities.Habitacion;
import exception.HabitacionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.HabitacionRepository;

import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> obtenerTodasLasHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Habitacion obtenerHabitacionPorId(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new HabitacionNotFoundException("Habitación no encontrada con ID: " + id));
    }

    public Habitacion crearHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Habitacion actualizarHabitacion(Long id, Habitacion habitacion) {
        if (!habitacionRepository.existsById(id)) {
            throw new HabitacionNotFoundException("Habitación no encontrada con ID: " + id);
        }
        habitacion.setId(id);
        return habitacionRepository.save(habitacion);
    }

    public void eliminarHabitacion(Long id) {
        if (!habitacionRepository.existsById(id)) {
            throw new HabitacionNotFoundException("Habitación no encontrada con ID: " + id);
        }
        habitacionRepository.deleteById(id);
    }
}

