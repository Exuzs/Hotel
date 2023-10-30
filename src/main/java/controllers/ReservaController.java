package controllers;

import entities.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaService.obtenerTodasLasReservas();
    }

    @GetMapping("/{id}")
    public Reserva obtenerReservaPorId(@PathVariable Long id) {
        return reservaService.obtenerReservaPorId(id);
    }

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaService.crearReserva(reserva);
    }

    @PutMapping("/{id}")
    public Reserva actualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        return reservaService.actualizarReserva(id, reserva);
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
    }
}
