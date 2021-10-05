package com.salesianostriana.Proyecto01.dto;

import com.salesianostriana.Proyecto01.model.Canciones;
import org.springframework.stereotype.Component;

@Component
public class CancionesDtoConverter {
    public Canciones createCancionesDtoToCanciones(CreateCancionesDto c) {
        return new Canciones(
                     c.getTitulo(),
                     c.getAlbum(),
                     c.getAnyo()
        );
    }
    public GetCancionesDto cancionesToGetCancionesDto(Canciones c) {
        return GetCancionesDto
                .builder().album(c.getAlbum())
                .anyo(c.getAnyo())
                .titulo(c.getTitulo())
                .build();
    }
}
