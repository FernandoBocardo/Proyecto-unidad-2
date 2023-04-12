package com.example.rest.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotNull(message = "El nombre del cliente no puede ser nulo")
    private String nombre;
    
    @Column
    @NotNull(message = "El correo del cliente no puede ser nulo")
    private String correo;
    
    @Column
    @NotNull(message = "El RFC del cliente no puede ser nulo")
    private String RFC;
    
    @Column
    @NotNull(message = "El telefono del cliente no puede ser nulo")
    private String telefono;
    
    @Column
    @NotNull(message = "La contrasena del cliente no puede ser nulo")
    private String contrasena;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String correo, String RFC, String telefono, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.RFC = RFC;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }

    public Cliente(String nombre, String correo, String RFC, String telefono, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.RFC = RFC;
        this.telefono = telefono;
        this.contrasena = contrasena;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
}
