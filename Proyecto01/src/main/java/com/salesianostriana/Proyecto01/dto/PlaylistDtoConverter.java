package com.salesianostriana.Proyecto01.dto;

import com.salesianostriana.Proyecto01.model.Playlist;
import org.springframework.stereotype.Component;

@Component
public class PlaylistDtoConverter {

    public Playlist createPlaylistDtoToPlaylist (CreatePlaylistDto c) {
        return new Playlist(
                c.getNombre(),
                c.getDescripcion()
        );
    }
    public GetPlaylistDto playlistToGetPlaylistDto(Playlist p) {
        GetPlaylistDto result = new GetPlaylistDto();
        result.setNombre(p.getNombre());
        result.setDescripcion(p.getDescripcion());
        result.setCanciones(p.getCanciones().getTitulo());
        return result;
    }
}
