package com.salesianostriana.Proyecto01.dto;

import com.salesianostriana.Proyecto01.model.Artista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateCancionDto {

    private String titulo;

    private String album;

    private Long artistaId;

    private String anyo;
}
