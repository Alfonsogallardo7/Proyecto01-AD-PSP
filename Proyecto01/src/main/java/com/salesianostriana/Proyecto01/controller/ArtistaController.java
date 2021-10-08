package com.salesianostriana.Proyecto01.controller;


import com.salesianostriana.Proyecto01.model.Artista;
import com.salesianostriana.Proyecto01.model.Cancion;
import com.salesianostriana.Proyecto01.repository.ArtistaRepository;
import com.salesianostriana.Proyecto01.repository.CancionRepository;
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

@RestController
@Api(tags = "ArtistaController")
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistaController {

    private final ArtistaRepository Arepository;
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @GetMapping("/")
    public ResponseEntity<List<Artista>> findAll(){
        return ResponseEntity
                .ok()
                .body(Arepository.findAll());

    }
    @ApiOperation(value = "Get2", notes = "devuelve los artistas por id")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND") })
    @GetMapping("/{id}")
    public ResponseEntity<Artista> findOne(@PathVariable Long id){
        return ResponseEntity
                .of(Arepository.findById(id));
    }
    @ApiOperation(value = "Post", notes = "crear un artista")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND") })
    @PostMapping("/")
    public ResponseEntity<Artista> create(@RequestBody Artista nuevoArtista){
        
        if (nuevoArtista.getNombre() == null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Arepository.save(nuevoArtista));
        }
    }
    @ApiOperation(value = "Put", notes = "actualizas un artista")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND") })
    @PutMapping("/{id}")
    public ResponseEntity<Artista> edit(@RequestBody Artista art,@PathVariable Long id){
        return ResponseEntity.of(
                Arepository.findById(id).map(a ->{
                    a.setNombre(art.getNombre());
                    Arepository.save(a);
                    return a;
                })
        );
    }
    @ApiOperation(value = "Delete", notes = "borras un artista")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "INTERNAL ERROR SERVER"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "UNAUTHORIZED"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "ELEMENTO NOT FOUND") })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Arepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
