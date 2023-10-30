package services;

import entities.Cliente;
import exception.ClienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente no encontrado con ID: " + id);
        }
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}

