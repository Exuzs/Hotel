package services;

import entities.Cliente;
import entities.Habitacion;
import entities.Reserva;
import entities.TipoHabitacion;
import exception.ReservaInvalidaException;
import exception.ReservaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ReservaRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private ClienteService clienteService;

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNotFoundException("Reserva no encontrada con ID: " + id));
    }

    public Reserva crearReserva(Reserva reserva) {
        if (reserva.getFechaReserva() == null || reserva.getFechaReserva().isBefore(LocalDate.now())) {
            throw new ReservaInvalidaException("La fecha de reserva no es válida.");
        }

        Cliente cliente = clienteService.obtenerClientePorId(reserva.getCliente().getId());

        Habitacion habitacion = habitacionService.obtenerHabitacionPorId(reserva.getHabitacion().getId());if (!habitacion.estaDisponibleParaFechas(reserva.getFechaReserva(), reserva.getFechaReserva().plusDays(1))) {
            throw new ReservaInvalidaException("La habitación no está disponible para el rango de fechas de reserva.");
        }

        BigDecimal precioBase = habitacion.getPrecioBase();

        int diasDeAntelacion = (int) LocalDate.now().until(reserva.getFechaReserva()).getDays();

        BigDecimal descuento = BigDecimal.ZERO;
        if (diasDeAntelacion > 15) {
            descuento = precioBase.multiply(new BigDecimal("0.20")); // 20% de descuento
        }

        if (habitacion.getTipoHabitacion().equals(TipoHabitacion.PREMIUM)) {
            descuento = descuento.add(precioBase.multiply(new BigDecimal("0.05"))); // 5% de descuento adicional
        }

        BigDecimal totalAPagar = precioBase.subtract(descuento);

        String numeroReserva = UUID.randomUUID().toString();

        reserva.setNumeroReserva(numeroReserva);
        reserva.setTotalAPagar(totalAPagar);

        return reservaRepository.save(reserva);
    }

    public Reserva actualizarReserva(Long id, Reserva reserva) {
        if (!reservaRepository.existsById(id)) {
            throw new ReservaNotFoundException("Reserva no encontrada con ID: " + id);
        }
        reserva.setId(id);
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new ReservaNotFoundException("Reserva no encontrada con ID: " + id);
        }
        reservaRepository.deleteById(id);
    }
}
