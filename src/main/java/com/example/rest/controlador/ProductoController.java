package com.example.rest.controlador;

import com.example.rest.entidad.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;
import com.example.rest.repositorio.IProductoRepositorio;

@Controller
@RequestMapping("/productos")
public class ProductoController 
{
    @Autowired
    private IProductoRepositorio iProductoRepositorio;
    
    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) 
    {
        if (producto.getId() != null) 
        {
            return ResponseEntity.badRequest().build();
        }
        Producto producto1 = iProductoRepositorio.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto1);
    }

    @GetMapping("/marca={marca}")
    public ResponseEntity<List<Producto>> obtenerProductosPorMarca(@RequestParam(value = "marca", required = false) String marca) 
    {
        List<Producto> productos;
        if (marca == null) 
        {
            productos = iProductoRepositorio.findAll();
        } 
        else 
        {
            productos = iProductoRepositorio.findByMarcaContainingIgnoreCase(marca);
        }
        if (productos.isEmpty()) 
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/codigo={codigo}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCodigo(@RequestParam(value = "codigo", required = false) String codigo) 
    {
        List<Producto> productos;
        if (codigo == null) 
        {
            productos = iProductoRepositorio.findAll();
        } 
        else 
        {
            productos = iProductoRepositorio.findByCodigoContainingIgnoreCase(codigo);
        }
        if (productos.isEmpty()) 
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable("id") Long id) 
    {
        Optional<Producto> producto = iProductoRepositorio.findById(id);
        if (producto.isPresent()) 
        {
            return ResponseEntity.ok(producto.get());
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") Long id, @RequestBody Producto productoActualizar) 
    {
        Optional<Producto> productoExiste = iProductoRepositorio.findById(id);
        if (productoExiste.isPresent()) 
        {
            Producto producto = productoExiste.get();
            if (productoActualizar.getNombre() != null) 
            {
                producto.setNombre(productoActualizar.getNombre());
            }
            if (productoActualizar.getMarca()!= null) 
            {
                producto.setMarca(productoActualizar.getMarca());
            }
            if (productoActualizar.getUnidadMedida()!= null) 
            {
                producto.setUnidadMedida(productoActualizar.getUnidadMedida());
            }
//            if (!(productoActualizar.getPrecio() < 0)) 
//            {
//                producto.setPrecio(productoActualizar.getPrecio());
//            }
            if (productoActualizar.getCodigo()!= null) 
            {
                producto.setCodigo(productoActualizar.getCodigo());
            }
            iProductoRepositorio.save(producto);
            return ResponseEntity.ok(producto);
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long id)
    {
        Optional<Producto> productoExiste = iProductoRepositorio.findById(id);
        if (productoExiste.isPresent()) 
        {
            iProductoRepositorio.delete(productoExiste.get());
            return ResponseEntity.noContent().build();
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }
}



