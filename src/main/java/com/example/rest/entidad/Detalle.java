package com.example.rest.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Detalle")
public class Detalle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "idVenta", nullable = false)
    private Venta idVenta;
    
    @ManyToOne()
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto idProducto;
    
    @Column
    @NotNull(message = "La cantidad no puede ser nula")
    private float cantidad;
    
    @Column
    @NotNull(message = "El total no puede ser nulo")
    private float total;

    public Detalle() {
    }

    public Detalle(Long id, Venta idVenta, Producto idProducto, float cantidad, float total) {
        this.id = id;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Detalle(Venta idVenta, Producto idProducto, float cantidad, float total) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
