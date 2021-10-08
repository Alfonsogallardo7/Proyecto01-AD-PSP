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

@RestController
@Api(tags = "Los controladores de las listas")
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlayListController {

    private final PlaylistRepository playlistRepository;
    private final PlaylistDtoConverter dtoConverter;
    private final CancionRepository cancionRepository;
    @ApiOperation(value = "Get", notes = "Obtiene todas las listas creadas")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se han mostrado todas las listas"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se han encontrado ninguna lista") })

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


    @ApiOperation(value = "Post", notes = "Crea una playlist")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha agragado una nueva playlist"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se han encontrado ninguna playlist") })
    @PostMapping("/")
    public ResponseEntity <Playlist> create(@RequestBody CreatePlaylistDto dto) {


        Playlist nuevo = dtoConverter.createPlaylistDtoToPlaylist(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playlistRepository.save(nuevo));

    }

    @ApiOperation(value = "Get", notes = "este get devuelve la playlist por id")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })


    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findOne(@PathVariable Long id){

            return ResponseEntity.of(playlistRepository.findById(id));
    }


    @ApiOperation(value = "Put", notes = "modifica las playlist")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "problemas en la modificacion"),
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
    @ApiOperation(value = "Delete", notes = "borrara las playlist")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Elemento borrado") })

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playlistRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Get", notes = "ver la cancion elegida en una playlist existente elegida")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @GetMapping("/{id}/songs/{id}")
    public ResponseEntity<Cancion> buscarCancionPlaylist(@PathVariable Long idplaylist, @PathVariable Long idcancion){
        return ResponseEntity.of(cancionRepository.findById(idcancion));
    }
    @ApiOperation(value = "Post", notes = "este post añade una cancion existente a una lista de reproduccion")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @PostMapping("/{idPlaylist}/songs/{idCancion}")
    public ResponseEntity<Playlist> nuevacancionplaylist(@PathVariable Long idPlaylist,@PathVariable Long idCancion) {
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

    @ApiOperation(value = "Get", notes = "Muestra todas las canciones de una playlist existente")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @GetMapping("{id}/songs/")
    public ResponseEntity<List<Cancion>> listarTodasCancionesPlaylist(@PathVariable Long id) {

        Optional<Playlist> playlistBuscada = playlistRepository.findById(id);

        if ( playlistBuscada != null) {
            return ResponseEntity.ok().body(playlistBuscada.get().getCanciones());

        }
        else {

            return  ResponseEntity
                    .ok()
                    .body(playlistBuscada.get().getCanciones());

        }



    }

    @DeleteMapping("{idPlaylist}/songs/{idCancion}")
    public ResponseEntity<?> removePlaylist(@PathVariable Long idPlaylist, @PathVariable Long idCancion){

        if(playlistRepository.getById(idPlaylist) != null){
            playlistRepository.getById(idPlaylist).deleteCancionPlaylist(idCancion);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }





}
