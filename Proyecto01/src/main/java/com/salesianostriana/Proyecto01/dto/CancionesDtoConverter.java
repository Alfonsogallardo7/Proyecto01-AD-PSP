package com.salesianostriana.Proyecto01.dto;

import com.salesianostriana.Proyecto01.model.Canciones;
import org.springframework.stereotype.Component;

@Component
public class CancionesDtoConverter {
    public Canciones createCancionesDtoToCanciones(CreateCancionesDto c){
        return new Canciones(
                     c.getTitulo(),
                     c.getArtista(),
                     c.getAlbum(),
                     c.getAnyo()
        );
    }
}
