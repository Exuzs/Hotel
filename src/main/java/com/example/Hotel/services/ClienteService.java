package com.example.Hotel.services;

import com.example.Hotel.entities.Cliente;
import com.example.Hotel.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> updateCliente(Long id, Cliente updatedCliente) {
        Optional<Cliente> existingCliente = clienteRepository.findById(id);
        if (existingCliente.isPresent()) {
            Cliente cliente = existingCliente.get();
            cliente.setNombre(updatedCliente.getNombre());
            cliente.setApellido(updatedCliente.getApellido());
            cliente.setCedula(updatedCliente.getCedula());
            cliente.setDireccion(updatedCliente.getDireccion());
            cliente.setEdad(updatedCliente.getEdad());
            cliente.setCorreoElectronico(updatedCliente.getCorreoElectronico());
            return Optional.of(clienteRepository.save(cliente));
        }
        return Optional.empty();
    }

    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

