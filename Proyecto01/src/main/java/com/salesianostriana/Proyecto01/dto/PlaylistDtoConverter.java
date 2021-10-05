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
}
