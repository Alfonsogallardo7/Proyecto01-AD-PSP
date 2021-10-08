package com.salesianostriana.Proyecto01.controller;

import com.salesianostriana.Proyecto01.dto.CreatePlaylistDto;
import com.salesianostriana.Proyecto01.dto.PlaylistDtoConverter;
import com.salesianostriana.Proyecto01.model.Cancion;
import com.salesianostriana.Proyecto01.model.Playlist;
import com.salesianostriana.Proyecto01.repository.CancionRepository;
import com.salesianostriana.Proyecto01.repository.PlaylistRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@Api(tags = "PlayListController")
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlayListController {

    private final PlaylistRepository playlistRepository;
    private final PlaylistDtoConverter dtoConverter;
    private final CancionRepository cancionRepository;
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

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
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

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
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })


    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findOne(@PathVariable Long id){

            return ResponseEntity.of(playlistRepository.findById(id));
    }
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

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
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playlistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @GetMapping("/{id}/songs/{id}")
    public ResponseEntity<Stream<Cancion>> buscarCancionPlaylist(@PathVariable Long idplaylist, @PathVariable Long idcancion){
        return ResponseEntity.of(playlistRepository.findById(idplaylist)
                .map(m -> (m.getCanciones().stream().filter(song -> song.getId().equals(idcancion)))
                ));

    }
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

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

    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @GetMapping("{id}/songs/")
    public ResponseEntity<Stream<Cancion>> cancionesDePlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {


        if (playlistRepository.findById(id) == null) {
            return ResponseEntity.badRequest().build();
        } else {

            return  ResponseEntity
                    .ok()
                    .body(playlist.getCanciones().stream().map(songs ->{
                        songs.getTitulo();
                        songs.getNombreArtista();
                        songs.getAlbum();
                        return songs;
                    }));
        }

    }



}
