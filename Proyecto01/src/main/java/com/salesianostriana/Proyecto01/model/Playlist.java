package com.salesianostriana.Proyecto01.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @ManyToOne (fetch = FetchType.EAGER)
    private Canciones canciones;

    public Playlist(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
