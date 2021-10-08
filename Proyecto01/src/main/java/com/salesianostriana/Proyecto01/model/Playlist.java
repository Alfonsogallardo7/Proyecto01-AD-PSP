package com.salesianostriana.Proyecto01.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @ManyToMany
    private List<Cancion> canciones;

    public Playlist(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void addCancion(Cancion c){
        canciones.add(c);

    }

    public void deleteCancionPlaylist(Long id){

        Iterator<Cancion> it = canciones.iterator();

        while(it.hasNext()){
            Cancion e=it.next();
            if(id.equals(e.getId()))
                it.remove();
        }
    }

}