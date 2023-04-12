package com.example.rest.repositorio;

import com.example.rest.entidad.Venta;
import java.util.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVentaRepositorio extends JpaRepository<Venta,Long> {
    List<Venta> findByFolioContainingIgnoreCase(String folio);
    List<Venta> findByFecha(Calendar fecha);
    List<Venta> findByIdCliente(Long IdCliente);
}
