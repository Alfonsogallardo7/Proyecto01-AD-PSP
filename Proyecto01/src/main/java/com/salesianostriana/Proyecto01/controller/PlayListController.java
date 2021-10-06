package com.salesianostriana.Proyecto01.controller;

import com.salesianostriana.Proyecto01.dto.CreatePlaylistDto;
import com.salesianostriana.Proyecto01.dto.PlaylistDtoConverter;
import com.salesianostriana.Proyecto01.model.Playlist;
import com.salesianostriana.Proyecto01.repository.CancionesRepository;
import com.salesianostriana.Proyecto01.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlayListController {

    private final PlaylistRepository playlistRepository;
    private final PlaylistDtoConverter dtoConverter;
    private final CancionesRepository cancionesRepository;

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
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> showOneWithSongs(@PathVariable Long id, @RequestBody CreatePlaylistDto dto){
        Optional<Playlist> data = playlistRepository.findById(id);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.of(
                    data.map(a->{
                        a.getCanciones();
                        playlistRepository.save(a);
                        return a;
                    })
            );
        }
    }

    @PostMapping("/")
    public ResponseEntity <Playlist> create(@RequestBody CreatePlaylistDto dto) {

        if (dto.getCancionesId() == null) {
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

    public ResponseEntity<Playlist> addSong(@RequestBody CreatePlaylistDto dto, @PathVariable Long id1, Long id2){

            return ResponseEntity.of(
                    playlistRepository.findById(id1).map(a->{
                        a.setCanciones(cancionesRepository.getById(id2));
                        playlistRepository.save(a);
                        return a;
                    })
            );

        }


    public ResponseEntity<Playlist> edit(@RequestBody Playlist playlist, @PathVariable Long id){


        return ResponseEntity.of(
                playlistRepository.findById(id).map(a->{
                    a.setDescripcion(playlist.getDescripcion());
                    a.setNombre(playlist.getNombre());
                    a.setCanciones(playlist.getCanciones());
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
