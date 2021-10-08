package com.salesianostriana.Proyecto01.dto;

import com.salesianostriana.Proyecto01.model.Cancion;
import org.springframework.stereotype.Component;

@Component
public class CancionDtoConverter {
    public Cancion createCancionDtoToCanciones(CreateCancionDto c) {
        return new Cancion(
                     c.getTitulo(),
                     c.getAlbum(),
                     c.getAnyo()
        );
    }
    public GetCancionDto cancionToGetCancionesDto(Cancion c) {
        return GetCancionDto
                .builder().album(c.getAlbum())
                .anyo(c.getAnyo())
                .titulo(c.getTitulo())
                .nombreArtista(c.getArtist()!=null?c.getArtist().getNombre():null)
                .build();
    }
}
