package com.salesianostriana.Proyecto01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaylistDto {

        private String nombre;

        private String descripcion;

        private Long cancionId;
}
