package com.salesianostriana.Proyecto01.model;


import com.salesianostriana.Proyecto01.model.Artista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Canciones {
    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artista artista;

    private String album;

    private String anyo;

    public Canciones(String titulo, String album, String anyo) {
        this.titulo = titulo;
        this.album = album;
        this.anyo = anyo;
    }
}
