package com.example.rest.repositorio;

import com.example.rest.entidad.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEstudianteRepositorio extends JpaRepository<Estudiante,Long> {
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);
    List<Estudiante> findByEdadContainingIgnoreCase(String edad);
}
