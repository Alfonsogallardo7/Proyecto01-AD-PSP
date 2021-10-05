package com.salesianostriana.Proyecto01.dto;

import com.salesianostriana.Proyecto01.model.Artista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateCancionesDto {

    private String titulo;

    private String album;

    private Artista artista;

    private String anyo;
}
