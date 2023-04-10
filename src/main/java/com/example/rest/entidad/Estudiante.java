package com.example.rest.entidad;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "El nombre del producto no puede ser nulo")
    private String nombre;

    @Column
    @NotNull(message = "La edad no puede ser nulo")
    private String edad;

    public Estudiante() {
    }

    public Estudiante(Long id, String nombre, String edad) 
    {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Estudiante(String nombre, String edad)
    {
        this.nombre = nombre;
        this.edad = edad;
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

    public String getEdad() 
    {
        return edad;
    }

    public void setEdad(String edad) 
    {
        this.edad = edad;
    }
}
