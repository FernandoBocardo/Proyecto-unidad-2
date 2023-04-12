package com.example.rest.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inventario")
public class Inventario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    private Producto idProducto;
    
    @Column
    @NotNull(message = "La cantidad no puede ser nula")
    private float cantidad;

    public Inventario() {
    }

    public Inventario(Long id, Producto idProducto, float cantidad) {
        this.id = id;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Inventario(Producto idProducto, float cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
}
