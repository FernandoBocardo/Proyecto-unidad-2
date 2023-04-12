package com.example.rest.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Producto")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "El nombre del producto no puede ser nulo")
    private String nombre;

    @Column
    @NotNull(message = "La marca del producto no puede ser nulo")
    private String marca;
    
    @ManyToOne()
    @JoinColumn(name = "unidadMedida", nullable = false)
    private UnidadMedida unidadMedida;
    
    @Column
    @NotNull(message = "El precio no puede ser nulo")
    private float precio;
    
    @Column
    @NotNull(message = "El codigo no puede ser nulo")
    private String codigo;

    public Producto() {
    }

    public Producto(Long id, String nombre, String marca, UnidadMedida unidadMedida, float precio, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.unidadMedida = unidadMedida;
        this.precio = precio;
        this.codigo = codigo;
    }

    public Producto(String nombre, String marca, UnidadMedida unidadMedida, float precio, String codigo) {
        this.nombre = nombre;
        this.marca = marca;
        this.unidadMedida = unidadMedida;
        this.precio = precio;
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
