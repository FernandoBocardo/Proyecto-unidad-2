package com.example.rest.repositorio;

import com.example.rest.entidad.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IInventarioRepositorio extends JpaRepository<Inventario,Long>{
    List<Inventario> findByIdProductoContainingIgnoreCase(Long idProducto);
}
