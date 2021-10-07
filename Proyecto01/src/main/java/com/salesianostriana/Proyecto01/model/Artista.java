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

    @OneToMany
    private List<Cancion> canciones = new ArrayList<>();

    public void addCancion(Cancion c){
        canciones.add(c);
    }

    @PreRemove
    public void setArtistNull(){
        for(Cancion c: canciones){
            c.setArtista(null);
        }
    }


}
