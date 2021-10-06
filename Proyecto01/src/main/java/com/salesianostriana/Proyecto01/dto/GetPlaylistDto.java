package com.salesianostriana.Proyecto01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPlaylistDto {
    private String nombre;

    private String descripcion;

    private String canciones;


}
