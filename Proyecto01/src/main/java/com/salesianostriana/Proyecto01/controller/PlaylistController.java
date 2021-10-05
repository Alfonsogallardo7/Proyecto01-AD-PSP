package com.salesianostriana.Proyecto01.controller;

import com.salesianostriana.Proyecto01.dto.CreateCancionesDto;
import com.salesianostriana.Proyecto01.dto.PlaylistDtoConverter;
import com.salesianostriana.Proyecto01.model.Playlist;
import com.salesianostriana.Proyecto01.repository.CancionesRepository;
import com.salesianostriana.Proyecto01.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistRepository playlistRepository;
    private final PlaylistDtoConverter dtoConverter;
    private final CancionesRepository cancionesRepository;

    @GetMapping("/")
    public ResponseEntity<List<Playlist>> findAll(){
        return ResponseEntity
                .ok()
                .body(playlistRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity <Playlist> create(@RequestBody CreateCancionesDto dto) {

    }

}
