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
@Api(tags = "Los controladores de artista")
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistaController {

    private final ArtistaRepository Arepository;
    @ApiOperation(value = "Get", notes = "Devuelve una lista con todos los artistas")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se a encontrado la lista de artistas"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha encontrado la lista de artistas") })

    @GetMapping("/")
    public ResponseEntity<List<Artista>> findAll(){
        return ResponseEntity
                .ok()
                .body(Arepository.findAll());

    }
    @ApiOperation(value = "Get", notes = "Devuelve el artista dependiendo del id que se le haya pasado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se a encontrado el artista"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha encontrado el artista") })
    @GetMapping("/{id}")
    public ResponseEntity<Artista> findOne(@PathVariable Long id){
        return ResponseEntity
                .of(Arepository.findById(id));
    }
    @ApiOperation(value = "Post", notes = "Crea un nuevo artista")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha añadido el artista"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "No se ha podido añadir el artista, algún errror en los datos") })
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
    @ApiOperation(value = "Put", notes = "Modifica al artista seleccionado")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Se ha modificado correctamente el artista seleccionado"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No se ha podido modificar, no existe el artista seleccionado") })
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
    @ApiOperation(value = "Delete", notes = "Borra el artista seleccionado, sin borrar las canciones")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Se ha borrado correctamente")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Arepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
