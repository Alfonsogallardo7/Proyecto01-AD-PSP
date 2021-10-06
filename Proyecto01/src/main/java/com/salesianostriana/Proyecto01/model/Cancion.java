package com.salesianostriana.Proyecto01.model;


import com.salesianostriana.Proyecto01.model.Artista;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Cancion {
    @Id
    @GeneratedValue
    private Long id;

    private String titulo;
    @ManyToOne
    private Artista artista;

    private String album;

    private String anyo;

    public Cancion(String titulo, String album, String anyo) {
        this.titulo = titulo;
        this.album = album;
        this.anyo = anyo;
    }
}
