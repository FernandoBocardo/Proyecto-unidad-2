package com.example.rest.controlador;

import com.example.rest.entidad.Cliente;
import com.example.rest.repositorio.IClienteRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private IClienteRepositorio iClienteRepositorio;
    
    @PostMapping
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) 
    {
        if (cliente.getId() != null) 
        {
            return ResponseEntity.badRequest().build();
        }
        Cliente cliente1 = iClienteRepositorio.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente1);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientesPorRFC(@RequestParam(value = "RFC", required = false) String RFC) 
    {
        List<Cliente> clientes;
        if (RFC == null) 
        {
            clientes = iClienteRepositorio.findAll();
        } 
        else 
        {
            clientes = iClienteRepositorio.findByRFCContainingIgnoreCase(RFC);
        }
        if (clientes.isEmpty()) 
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable("id") Long id) 
    {
        Optional<Cliente> cliente = iClienteRepositorio.findById(id);
        if (cliente.isPresent()) 
        {
            return ResponseEntity.ok(cliente.get());
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente clienteActualizar) 
    {
        Optional<Cliente> clienteExiste = iClienteRepositorio.findById(id);
        if (clienteExiste.isPresent()) 
        {
            Cliente cliente = clienteExiste.get();
            if (clienteActualizar.getNombre() != null) 
            {
                cliente.setNombre(clienteActualizar.getNombre());
            }
            if (clienteActualizar.getCorreo()!= null) 
            {
                cliente.setCorreo(clienteActualizar.getCorreo());
            }
            if (clienteActualizar.getRFC()!= null) 
            {
                cliente.setRFC(clienteActualizar.getRFC());
            }
            if (clienteActualizar.getTelefono()!= null) 
            {
                cliente.setTelefono(clienteActualizar.getTelefono());
            }
            if (clienteActualizar.getContrasena()!= null) 
            {
                cliente.setContrasena(clienteActualizar.getContrasena());
            }
            iClienteRepositorio.save(cliente);
            return ResponseEntity.ok(cliente);
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable("id") Long id)
    {
        Optional<Cliente> clienteExiste = iClienteRepositorio.findById(id);
        if (clienteExiste.isPresent()) 
        {
            iClienteRepositorio.delete(clienteExiste.get());
            return ResponseEntity.noContent().build();
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }
}
