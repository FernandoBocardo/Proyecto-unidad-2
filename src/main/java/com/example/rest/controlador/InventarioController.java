package com.example.rest.controlador;

import com.example.rest.entidad.Inventario;
import com.example.rest.entidad.Producto;
import com.example.rest.repositorio.IInventarioRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/inventarios")
public class InventarioController {
    
    @Autowired
    private IInventarioRepositorio iInventarioRepositorio;
    
    @PostMapping
    public ResponseEntity<Inventario> agregarInventario(@RequestBody Inventario inventario) 
    {
        if (inventario.getId() != null) 
        {
            return ResponseEntity.badRequest().build();
        }
        Inventario inventario1 = iInventarioRepositorio.save(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventario1);
    }

    @GetMapping("/idProducto={idProducto}")
    public List<Inventario> obtenerInventariosPorIdProducto(@PathVariable Long idProducto)
    {
        return iInventarioRepositorio.findByIdProducto(idProducto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerInventarioPorId(@PathVariable("id") Long id) 
    {
        Optional<Inventario> inventario = iInventarioRepositorio.findById(id);
        if (inventario.isPresent()) 
        {
            return ResponseEntity.ok(inventario.get());
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable("id") Long id, @RequestBody Inventario inventarioActualizar) 
    {
        Optional<Inventario> inventarioExiste = iInventarioRepositorio.findById(id);
        if (inventarioExiste.isPresent()) 
        {
            Inventario inventario = inventarioExiste.get();
            if (!(inventarioActualizar.getCantidad() < 0)) 
            {
                inventario.setCantidad(inventarioActualizar.getCantidad());
            }
            if (inventarioActualizar.getIdProducto()!= null) 
            {
                inventario.setIdProducto(inventarioActualizar.getIdProducto());
            }
            iInventarioRepositorio.save(inventario);
            return ResponseEntity.ok(inventario);
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable("id") Long id)
    {
        Optional<Inventario> inventarioExiste = iInventarioRepositorio.findById(id);
        if (inventarioExiste.isPresent()) 
        {
            iInventarioRepositorio.delete(inventarioExiste.get());
            return ResponseEntity.noContent().build();
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }
}
