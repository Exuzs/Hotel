package controllers;

import entities.Habitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.HabitacionService;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public List<Habitacion> obtenerTodasLasHabitaciones() {
        return habitacionService.obtenerTodasLasHabitaciones();
    }

    @GetMapping("/{id}")
    public Habitacion obtenerHabitacionPorId(@PathVariable Long id) {
        return habitacionService.obtenerHabitacionPorId(id);
    }

    @PostMapping
    public Habitacion crearHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.crearHabitacion(habitacion);
    }

    @PutMapping("/{id}")
    public Habitacion actualizarHabitacion(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        return habitacionService.actualizarHabitacion(id, habitacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarHabitacion(@PathVariable Long id) {
        habitacionService.eliminarHabitacion(id);
    }
}

