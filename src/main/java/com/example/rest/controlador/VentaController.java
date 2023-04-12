package com.example.rest.controlador;

import com.example.rest.entidad.Venta;
import com.example.rest.repositorio.IVentaRepositorio;
import java.util.Calendar;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ventas")
public class VentaController 
{
    @Autowired
    private IVentaRepositorio iVentaRepositorio;
    
    @PostMapping
    public ResponseEntity<Venta> agregarVenta(@RequestBody Venta venta) 
    {
        if (venta.getId() != null) 
        {
            return ResponseEntity.badRequest().build();
        }
        Venta venta1 = iVentaRepositorio.save(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(venta1);
    }

    @GetMapping("/folio={folio}")
    public ResponseEntity<List<Venta>> obtenerVentasPorFolio(@RequestParam(value = "folio", required = false) String folio) 
    {
        List<Venta> ventas;
        if (folio == null) 
        {
            ventas = iVentaRepositorio.findAll();
        } 
        else 
        {
            ventas = iVentaRepositorio.findByFolioContainingIgnoreCase(folio);
        }
        if (ventas.isEmpty()) 
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/fecha={fecha}")
    public ResponseEntity<List<Venta>> obtenerVentasPorFecha(@RequestParam(value = "fecha", required = false) Calendar fecha) 
    {
        List<Venta> ventas;
        if (fecha == null) 
        {
            ventas = iVentaRepositorio.findAll();
        } 
        else 
        {
            ventas = iVentaRepositorio.findByFecha(fecha);
        }
        if (ventas.isEmpty()) 
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/idCliente={idCliente}")
    public List<Venta> obtenerInventariosPorIdProducto(@PathVariable Long idCliente)
    {
        return iVentaRepositorio.findByIdCliente(idCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable("id") Long id) 
    {
        Optional<Venta> venta = iVentaRepositorio.findById(id);
        if (venta.isPresent()) 
        {
            return ResponseEntity.ok(venta.get());
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable("id") Long id, @RequestBody Venta ventaActualizar) 
    {
        Optional<Venta> ventaExiste = iVentaRepositorio.findById(id);
        if (ventaExiste.isPresent()) 
        {
            Venta venta = ventaExiste.get();
            if (ventaActualizar.getIdCliente()!= null) 
            {
                venta.setIdCliente(ventaActualizar.getIdCliente());
            }
            if (!(ventaActualizar.getTotal() < 0)) 
            {
                venta.setTotal(ventaActualizar.getTotal());
            }
            if (ventaActualizar.getFolio()!= null) 
            {
                venta.setFolio(ventaActualizar.getFolio());
            }
            if (ventaActualizar.getFecha()!= null) 
            {
                venta.setFecha(ventaActualizar.getFecha());
            }
            iVentaRepositorio.save(venta);
            return ResponseEntity.ok(venta);
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable("id") Long id)
    {
        Optional<Venta> ventaExiste = iVentaRepositorio.findById(id);
        if (ventaExiste.isPresent()) 
        {
            iVentaRepositorio.delete(ventaExiste.get());
            return ResponseEntity.noContent().build();
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }
}



