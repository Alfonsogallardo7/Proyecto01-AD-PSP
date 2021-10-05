package com.salesianostriana.Proyecto01.controller;

import com.salesianostriana.Proyecto01.dto.CancionesDtoConverter;
import com.salesianostriana.Proyecto01.dto.CreateCancionesDto;
import com.salesianostriana.Proyecto01.model.Artista;
import com.salesianostriana.Proyecto01.model.Canciones;
import com.salesianostriana.Proyecto01.repository.ArtistaRepository;
import com.salesianostriana.Proyecto01.repository.CancionesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/canciones")
public class CancionesController {

    private final CancionesRepository Crepository;
    private final CancionesDtoConverter dtoConverter;
    private final ArtistaRepository artistaRepository;

    @PostMapping("/")
    public ResponseEntity <Canciones> create (@RequestBody CreateCancionesDto dto) {
        if (dto.getArtistaid() ==null) {
            return ResponseEntity.badRequest().build();
        }

        Canciones nuevo = dtoConverter.createCancionesDtoToCanciones(dto);

        Artista artista = artistaRepository.findById(dto.getArtistaid()).orElse(null);

        nuevo.setArtista(artista);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Crepository.save(nuevo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Canciones> findOne(@PathVariable Long id) {


        return ResponseEntity.of(Crepository.findById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Crepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
