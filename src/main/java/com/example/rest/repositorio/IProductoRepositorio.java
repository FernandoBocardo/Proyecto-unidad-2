package com.example.rest.repositorio;

import com.example.rest.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto,Long> {
    List<Producto> findByMarcaContainingIgnoreCase(String marca);
    List<Producto> findByCodigoContainingIgnoreCase(String codigo);
}
