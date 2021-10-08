package com.salesianostriana.Proyecto01.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Artista {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @OneToMany(fetch = FetchType.EAGER,orphanRemoval = false)
    @Column(nullable = true)
    @JsonManagedReference
    private List<Cancion> canciones = new ArrayList<>();

    public void addCancion(Cancion c){
        canciones.add(c);
    }

    public void deleteCancion(Long id){

        Iterator<Cancion> it = canciones.iterator();

        while(it.hasNext()){
            Cancion e=it.next();
            if(id.equals(e.getId()))
                it.remove();
        }
    }

    @PreRemove
    public void setArtistNull(){
        for(Cancion c: canciones){
            c.setArtist(null);
        }
        canciones.clear();
    }


}
