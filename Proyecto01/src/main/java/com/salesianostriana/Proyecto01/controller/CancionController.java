package com.salesianostriana.Proyecto01.controller;

import com.salesianostriana.Proyecto01.dto.CancionDtoConverter;
import com.salesianostriana.Proyecto01.dto.GetCancionDto;
import com.salesianostriana.Proyecto01.dto.CreateCancionDto;
import com.salesianostriana.Proyecto01.model.Artista;
import com.salesianostriana.Proyecto01.model.Cancion;
import com.salesianostriana.Proyecto01.repository.ArtistaRepository;
import com.salesianostriana.Proyecto01.repository.CancionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cancion")
public class CancionController {

    private final CancionRepository crepository;
    private final CancionDtoConverter dtoConverter;
    private final ArtistaRepository artistaRepository;


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

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> findOne(@PathVariable Long id) {


        return ResponseEntity.of(crepository.findById(id));

    }



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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Long idArtistaEncontrado;

        List<Artista> artistas = artistaRepository.findAll();

        for(Artista art : artistas){
            if(art.getNombre().equals(crepository.getById(id).getNombreArtista()))
                art.deleteCancion(id);
            else
                idArtistaEncontrado = null;
        }
        crepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
