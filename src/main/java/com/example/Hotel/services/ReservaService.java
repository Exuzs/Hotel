package com.example.Hotel.services;

import com.example.Hotel.entities.Reserva;
import com.example.Hotel.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> updateReserva(Long id, Reserva updatedReserva) {
        Optional<Reserva> existingReserva = reservaRepository.findById(id);
        if (existingReserva.isPresent()) {
            Reserva reserva = existingReserva.get();
            reserva.setFechaReserva(updatedReserva.getFechaReserva());
            reserva.setNumeroReserva(updatedReserva.getNumeroReserva());
            reserva.setTotalAPagar(updatedReserva.getTotalAPagar());
            return Optional.of(reservaRepository.save(reserva));
        }
        return Optional.empty();
    }

    public boolean deleteReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
