package com.salesianostriana.Proyecto01.controller;

import com.salesianostriana.Proyecto01.dto.CancionDtoConverter;
import com.salesianostriana.Proyecto01.dto.GetCancionDto;
import com.salesianostriana.Proyecto01.dto.CreateCancionDto;
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
import java.util.stream.Collectors;

@RestController
@Api(tags = "CancionController")
@RequiredArgsConstructor
@RequestMapping("/songs")
public class CancionController {

    private final CancionRepository crepository;
    private final CancionDtoConverter dtoConverter;
    private final ArtistaRepository artistaRepository;

    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @GetMapping("/")
    public ResponseEntity<List<GetCancionDto>> findAll(){
        List<Cancion> data = crepository.findAll();
        if(data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<GetCancionDto> resultado=
                    data.stream().map(dtoConverter::cancionToGetCancionesDto)
                            .collect(Collectors.toList());
            return ResponseEntity.ok().body(resultado);
        }
    }
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @PostMapping("/")
    public ResponseEntity <Cancion> create (@RequestBody CreateCancionDto dto) {
        if (dto.getArtistaId() == null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            Artista artista = artistaRepository.findById(dto.getArtistaId()).orElse(null);

            Cancion nuevo = dtoConverter.createCancionDtoToCanciones(dto);

            nuevo.setNombreArtista(artista.getNombre());

            artista.addCancion(nuevo);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(crepository.save(nuevo));
        }

    }
    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> findOne(@PathVariable Long id) {


        return ResponseEntity.of(crepository.findById(id));

    }


    @ApiOperation(value = "Get", notes = "este get devuelve todos los artistas que haya")
    @ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "OK"),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Error interno del servidor"),
            @ApiResponse(code = HttpServletResponse.SC_UNAUTHORIZED, message = "no autorizado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Elemento no encontrado") })

    @PutMapping("/{id}")
    public ResponseEntity<Cancion> edit(@RequestBody Cancion can, @PathVariable Long id){
        return ResponseEntity.of(
                crepository.findById(id).map(a ->{
                    a.setAlbum(can.getAlbum());
                    a.setAnyo(can.getAnyo());
                    a.setTitulo(can.getTitulo());
                    a.setNombreArtista(can.getNombreArtista());
                    crepository.save(a);
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

        crepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
