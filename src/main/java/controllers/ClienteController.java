package controllers;

import entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id);
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.actualizarCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }
}

