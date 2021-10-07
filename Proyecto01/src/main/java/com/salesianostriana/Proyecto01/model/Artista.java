package com.salesianostriana.Proyecto01.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    public void deleteCancion(Long id){

        for(Cancion can : canciones){
            if(can.getId()==id)
                canciones.remove(can);
        }
    }

    @PreRemove
    public void setArtistNull(){
        for(Cancion c: canciones){
            c.setNombreArtista(null);
        }
    }


}
