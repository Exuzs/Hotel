package com.example.Hotel.services;

import com.example.Hotel.entities.Habitacion;
import com.example.Hotel.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;

    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    @Transactional
    public List<Habitacion> getAllHabitaciones() {
        return habitacionRepository.findAll();
    }

    @Transactional
    public Optional<Habitacion> getHabitacionById(Long id) {
        return habitacionRepository.findById(id);
    }

    @Transactional
    public Optional<Habitacion> updateHabitacion(Long id, Habitacion habitacion) {
        Optional<Habitacion> existingHabitacion = habitacionRepository.findById(id);

        if (existingHabitacion.isPresent()) {
            habitacion.setId(id);
            return Optional.of(habitacionRepository.save(habitacion));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public Habitacion createHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Transactional
    public boolean deleteHabitacion(Long id) {
        Optional<Habitacion> habitacionOptional = habitacionRepository.findById(id);

        if (habitacionOptional.isPresent()) {
            Habitacion habitacion = habitacionOptional.get();
            habitacionRepository.delete(habitacion);

            return true;
        } else {
            return false;
        }
    }
}

