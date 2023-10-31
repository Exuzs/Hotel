package com.example.Hotel.controllers;

import com.example.Hotel.entities.Habitacion;
import com.example.Hotel.entities.TipoHabitacion;
import com.example.Hotel.services.HabitacionService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @Autowired
    private EntityManager entityManager;  // Inyectamos el EntityManager

    @GetMapping
    public ResponseEntity<List<Habitacion>> getAllHabitaciones() {
        List<Habitacion> habitaciones = habitacionService.getAllHabitaciones();
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> getHabitacionById(@PathVariable Long id) {
        Optional<Habitacion> habitacionOptional = habitacionService.getHabitacionById(id);
        if (habitacionOptional.isPresent()) {
            Habitacion habitacion = habitacionOptional.get();
            return new ResponseEntity<>(habitacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> updateHabitacion(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        Optional<Habitacion> updatedHabitacionOptional = habitacionService.updateHabitacion(id, habitacion);
        if (updatedHabitacionOptional.isPresent()) {
            Habitacion updatedHabitacion = updatedHabitacionOptional.get();
            return new ResponseEntity<>(updatedHabitacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Habitacion> createHabitacion(@RequestBody Habitacion habitacion) {
        // Asegurarse de que el TipoHabitacion esté guardado primero
        TipoHabitacion tipoHabitacion = habitacion.getTipoHabitacion();
        if (tipoHabitacion != null) {
            // Verificar si el TipoHabitacion ya tiene un ID (ya se guardó)
            if (tipoHabitacion.getId() == null) {
                // Si no se ha guardado, guárdalo primero
                entityManager.persist(tipoHabitacion);
            }
        }

        Habitacion nuevaHabitacion = habitacionService.createHabitacion(habitacion);
        return new ResponseEntity<>(nuevaHabitacion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Long id) {
        boolean deleted = habitacionService.deleteHabitacion(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

