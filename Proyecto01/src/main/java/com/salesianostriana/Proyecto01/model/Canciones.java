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
    private Long Id;

    private String Titulo;

    @ManyToOne
    private Artista Artista;

    private String Album;

    private String Anyo;

    public Canciones(String titulo, com.salesianostriana.Proyecto01.model.Artista artista, String album, String anyo) {
        Titulo = titulo;
        Artista = artista;
        Album = album;
        Anyo = anyo;
    }
}
