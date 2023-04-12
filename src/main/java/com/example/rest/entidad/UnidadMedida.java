package com.example.rest.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UnidadMedida")
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "El nombre de la unidad de medida no puede ser nulo")
    private String nombre;

    @Column
    @NotNull(message = "El codigo no puede ser nulo")
    private String codigo;

    public UnidadMedida() {
    }

    public UnidadMedida(Long id, String nombre, String codigo) 
    {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public UnidadMedida(String nombre, String codigo)
    {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getCodigo() 
    {
        return codigo;
    }

    public void setCodigo(String codigo) 
    {
        this.codigo = codigo;
    }
}

