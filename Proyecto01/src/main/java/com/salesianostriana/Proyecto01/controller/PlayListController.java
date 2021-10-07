package com.salesianostriana.Proyecto01.controller;

import com.salesianostriana.Proyecto01.dto.CreatePlaylistDto;
import com.salesianostriana.Proyecto01.dto.PlaylistDtoConverter;
import com.salesianostriana.Proyecto01.model.Cancion;
import com.salesianostriana.Proyecto01.model.Playlist;
import com.salesianostriana.Proyecto01.repository.CancionRepository;
import com.salesianostriana.Proyecto01.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlayListController {

    private final PlaylistRepository playlistRepository;
    private final PlaylistDtoConverter dtoConverter;
    private final CancionRepository cancionRepository;

    @GetMapping("/")
    public ResponseEntity<List<Playlist>> findAll(){
        List<Playlist> data = playlistRepository.findAll();
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.
                    ok()
                    .body(playlistRepository.findAll());
        }
    }
    @PostMapping("/")
    public ResponseEntity <Playlist> create(@RequestBody CreatePlaylistDto dto) {

        if (dto.getCancionId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Playlist nuevo = dtoConverter.createPlaylistDtoToPlaylist(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playlistRepository.save(nuevo));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findOne(@PathVariable Long id){

            return ResponseEntity.of(playlistRepository.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Playlist> edit(@RequestBody Playlist playlist, @PathVariable Long id){
        return ResponseEntity.of(
                playlistRepository.findById(id).map(a->{
                    a.setDescripcion(playlist.getDescripcion());
                    a.setNombre(playlist.getNombre());
                    a.setCancion(playlist.getCancion());
                    playlistRepository.save(a);
                    return a;
                })
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playlistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
