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
    private Long Id;

    private String Nombre;

    private String Descripcion;

    @ManyToOne
    private List<com.salesianostriana.Proyecto01.model.Canciones> Canciones;

}
