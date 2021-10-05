package com.salesianostriana.Proyecto01.model;


import com.salesianostriana.Proyecto01.model.Artista;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Canciones {
    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    @ManyToOne
    private Artista artista;

    private String album;

    private String anyo;

    public Canciones(String titulo, Artista artista, String album, String anyo) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.anyo = anyo;
    }

}
