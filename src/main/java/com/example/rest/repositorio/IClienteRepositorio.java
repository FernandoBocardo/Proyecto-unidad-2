package com.example.rest.repositorio;

import com.example.rest.entidad.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente,Long>{
    List<Cliente> findByRFCContainingIgnoreCase(String RFC);
}
