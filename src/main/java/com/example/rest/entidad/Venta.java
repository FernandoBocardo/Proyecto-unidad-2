package com.example.rest.entidad;

import java.util.Calendar;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Venta")
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente idCliente;
    
    @Column
    @NotNull(message = "El total no puede ser nulo")
    private float total;
    
    @Column
    @NotNull(message = "El folio no puede ser nulo")
    private String folio;
    
    @Column (name="fecha",nullable=false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar fecha;

    public Venta() {
    }

    public Venta(Long id, Cliente idCliente, float total, String folio, Calendar fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.total = total;
        this.folio = folio;
        this.fecha = fecha;
    }

    public Venta(Cliente idCliente, float total, String folio, Calendar fecha) {
        this.idCliente = idCliente;
        this.total = total;
        this.folio = folio;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
    
}
