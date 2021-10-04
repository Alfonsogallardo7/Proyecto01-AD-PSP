package com.salesianostriana.Proyecto01.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Playlist {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String descripcion;

    @ManyToOne
    private List<Canciones> canciones;

    public Playlist(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
