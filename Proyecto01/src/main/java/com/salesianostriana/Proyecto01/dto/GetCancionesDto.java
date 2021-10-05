package com.salesianostriana.Proyecto01.dto;

import com.salesianostriana.Proyecto01.model.Artista;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCancionesDto {

        private String titulo;

        private String album;

        private String anyo;
    }


