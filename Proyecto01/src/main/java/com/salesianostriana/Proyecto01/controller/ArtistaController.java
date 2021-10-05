package com.salesianostriana.Proyecto01.controller;


import com.salesianostriana.Proyecto01.model.Artista;
import com.salesianostriana.Proyecto01.repository.ArtistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistaController {

    private final ArtistaRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Artista>> findAll(){
        return ResponseEntity
                .ok()
                .body(repository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> findOne(@PathVariable Long id){
        return ResponseEntity
                .of(repository.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Artista> create(@RequestBody Artista nuevoArtista){


        /*if (nuevoArtista.getId() == null) {
            return ResponseEntity.badRequest().build();
        }*/
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(repository.save(nuevoArtista));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> edit(@RequestBody Artista art,@PathVariable Long id){
        return ResponseEntity.of(
                repository.findById(id).map(a ->{
                    a.setNombre(art.getNombre());
                    repository.save(a);
                    return a;
                })
        );
    }


}
