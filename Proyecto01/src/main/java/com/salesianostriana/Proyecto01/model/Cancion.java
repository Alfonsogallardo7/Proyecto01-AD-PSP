package com.salesianostriana.Proyecto01.model;


import com.salesianostriana.Proyecto01.model.Artista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.TypeReferenceAdjustment;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artista artista;

    private String album;

    private String anyo;

    public Cancion(String titulo, String album, String anyo) {
        this.titulo = titulo;
        this.album = album;
        this.anyo = anyo;
    }

}
