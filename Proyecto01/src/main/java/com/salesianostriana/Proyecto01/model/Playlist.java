package com.salesianostriana.Proyecto01.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Cancion cancion;

    public Playlist(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
