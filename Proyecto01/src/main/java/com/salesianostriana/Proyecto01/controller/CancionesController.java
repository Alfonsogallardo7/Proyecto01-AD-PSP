package com.salesianostriana.Proyecto01.controller;

import com.salesianostriana.Proyecto01.dto.CancionesDtoConverter;
import com.salesianostriana.Proyecto01.dto.GetCancionesDto;
import com.salesianostriana.Proyecto01.dto.CreateCancionesDto;
import com.salesianostriana.Proyecto01.model.Artista;
import com.salesianostriana.Proyecto01.model.Canciones;
import com.salesianostriana.Proyecto01.repository.ArtistaRepository;
import com.salesianostriana.Proyecto01.repository.CancionesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/canciones")
public class CancionesController {

    private final CancionesRepository crepository;
    private final CancionesDtoConverter dtoConverter;
    private final ArtistaRepository artistaRepository;


    @GetMapping("/")
    public ResponseEntity<List<GetCancionesDto>> findAll(){
        List<Canciones> data = crepository.findAll();
        if(data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<GetCancionesDto> resultado=
                    data.stream().map(dtoConverter::cancionesToGetCancionesDto)
                            .collect(Collectors.toList());
            return ResponseEntity.ok().body(resultado);
        }
    }

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
                .body(crepository.save(nuevo));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Canciones> findOne(@PathVariable Long id) {


        return ResponseEntity.of(crepository.findById(id));

    }



    @PutMapping("/{id}")
    public ResponseEntity<Canciones> edit(@RequestBody Canciones can,@PathVariable Long id){
        return ResponseEntity.of(
                crepository.findById(id).map(a ->{
                    a.setAlbum(can.getAlbum());
                    a.setAnyo(can.getAnyo());
                    a.setTitulo(can.getTitulo());
                    a.setArtista(can.getArtista());
                    crepository.save(a);
                    return a;
                })
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        crepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
