package com.salesianostriana.Proyecto01.model;


import com.salesianostriana.Proyecto01.model.Artista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cancion")
public class Cancion {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)

    private Long id;

    private String titulo;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Artista artista;

    private String album;

    private String anyo;

    public Cancion(String titulo, String album, String anyo) {
        this.titulo = titulo;
        this.album = album;
        this.anyo = anyo;
    }
}
