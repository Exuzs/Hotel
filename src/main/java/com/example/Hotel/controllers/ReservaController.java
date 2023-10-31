package com.example.Hotel.controllers;

import com.example.Hotel.entities.Reserva;
import com.example.Hotel.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            return ResponseEntity.ok(reserva.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva updatedReserva) {
        Optional<Reserva> existingReserva = reservaRepository.findById(id);
        if (existingReserva.isPresent()) {
            Reserva reserva = existingReserva.get();
            reserva.setFechaReserva(updatedReserva.getFechaReserva());
            reserva.setNumeroReserva(updatedReserva.getNumeroReserva());
            reserva.setTotalAPagar(updatedReserva.getTotalAPagar());
            reserva.setHabitacion(updatedReserva.getHabitacion());
            reserva.setCliente(updatedReserva.getCliente());
            reservaRepository.save(reserva);
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
