package com.example.rest.controlador;

import com.example.rest.entidad.Estudiante;
import com.example.rest.repositorio.IEstudianteRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController 
{
    @Autowired
    private IEstudianteRepositorio iEstudianteRepositorio;
    
    @PostMapping
    public ResponseEntity<Estudiante> agregarProducto(@RequestBody Estudiante estudiante) 
    {
        if (estudiante.getId() != null) 
        {
            return ResponseEntity.badRequest().build();
        }
        Estudiante estudiante1 = iEstudianteRepositorio.save(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudiante1);
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesPorNombre(@RequestParam(value = "nombre", required = false) String nombre) 
    {
        List<Estudiante> estudiantes;
        if (nombre == null) 
        {
            estudiantes = iEstudianteRepositorio.findAll();
        } 
        else 
        {
            estudiantes = iEstudianteRepositorio.findByNombreContainingIgnoreCase(nombre);
        }
        if (estudiantes.isEmpty()) 
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerEstudiantePorId(@PathVariable("id") Long id) 
    {
        Optional<Estudiante> estudiante = iEstudianteRepositorio.findById(id);
        if (estudiante.isPresent()) 
        {
            return ResponseEntity.ok(estudiante.get());
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable("id") Long id, @RequestBody Estudiante estudianteActualizar) 
    {
        Optional<Estudiante> estudianteExiste = iEstudianteRepositorio.findById(id);
        if (estudianteExiste.isPresent()) 
        {
            Estudiante estudiante = estudianteExiste.get();
            if (estudianteActualizar.getNombre() != null) 
            {
                estudiante.setNombre(estudianteActualizar.getNombre());
            }
            if (estudianteActualizar.getEdad() != null) 
            {
                estudiante.setEdad(estudianteActualizar.getEdad());
            }
            iEstudianteRepositorio.save(estudiante);
            return ResponseEntity.ok(estudiante);
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable("id") Long id)
    {
        Optional<Estudiante> estudianteExiste = iEstudianteRepositorio.findById(id);
        if (estudianteExiste.isPresent()) 
        {
            iEstudianteRepositorio.delete(estudianteExiste.get());
            return ResponseEntity.noContent().build();
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }
}



