package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BODEGAS")
public class Bodega {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bodega_seq")
    @SequenceGenerator(name = "bodega_seq", sequenceName = "BODEGA_SEQ", allocationSize = 1)
    private Long id;
    
    @Column(name = "CODIGO", nullable = false, unique = true, length = 50)
    private String codigo;
    
    @Column(name = "NOMBRE", nullable = false, length = 200)
    private String nombre;
    
    @Column(name = "DIRECCION", length = 300)
    private String direccion;
    
    @Column(name = "TELEFONO", length = 20)
    private String telefono;
    
    @Column(name = "CAPACIDAD_MAXIMA")
    private Integer capacidadMaxima;
    
    @Column(name = "ESPACIO_UTILIZADO")
    private Integer espacioUtilizado;
    
    @Column(name = "ACTIVO")
    private Boolean activo = true;
    
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (espacioUtilizado == null) {
            espacioUtilizado = 0;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }
    
    public Integer getEspacioUtilizado() {
        return espacioUtilizado;
    }
    
    public void setEspacioUtilizado(Integer espacioUtilizado) {
        this.espacioUtilizado = espacioUtilizado;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
    
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}