package com.salesianostriana.Proyecto01.dto;

import com.salesianostriana.Proyecto01.model.Artista;
import org.springframework.stereotype.Component;

@Component
public class ArtistaDtoConverter {

    public Artista createArtistaDtoToArtista(CreateArtistaDto c) {
        return new Artista(c.getNombre());
    }


    public GetArtistaDto artistaToGetArtistaDto(Artista a) {
        return GetArtistaDto.builder
    }
}
