package com.salesianostriana.Proyecto01.model;


import com.salesianostriana.Proyecto01.dto.GetCancionDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Artista {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "artista")
    private List<Cancion> canciones = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        for (Cancion c : canciones) {
            c.setArtista(null);
        }
    }

}
