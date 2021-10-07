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
import java.util.Optional;
import java.util.stream.Stream;

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
    /*
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
    */
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

    @GetMapping("/{id}/songs/{id}")
    public ResponseEntity<Stream<Cancion>> buscarCancionPlaylist(@PathVariable Long idplaylist, @PathVariable Long idcancion){
        return ResponseEntity.of(playlistRepository.findById(idplaylist)
                .map(m -> (m.getCanciones().stream().filter(song -> song.getId().equals(idcancion)))
                ));

    }
    @PostMapping("/{idPlaylist}/songs/{idCancion}")
    public ResponseEntity<Playlist>
    nuevacancionplaylist(@RequestBody Playlist playlist, @PathVariable Long idPlaylist,@PathVariable Long idCancion) {
        if ((playlistRepository.findById(idPlaylist) == null) || (cancionRepository.findById(idCancion) == null)){
            return ResponseEntity.badRequest().build();
        }else {
            Playlist playlist1 = playlistRepository.findById(idPlaylist).orElse(null);

            Cancion cancion1 = cancionRepository.findById(idCancion).orElse(null);
            playlist1.addCancion(cancion1);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(playlistRepository.save(playlist1));

        }

    }

    @GetMapping("{id}/songs/")
    public ResponseEntity<List<Cancion>> listarTodasCancionesPlaylist(@PathVariable Long id) {

        Playlist playlist1 = playlistRepository.findById(id).orElse(null);
        if (playlist1 == null) {
            return ResponseEntity.notFound().build();
        } else {



            return  ResponseEntity
                    .ok()
                    .body(playlist1.getCanciones());
        }

    }



}
