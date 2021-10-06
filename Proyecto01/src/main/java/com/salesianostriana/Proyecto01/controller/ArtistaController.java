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

    private final ArtistaRepository Arepository;

    @GetMapping("/")
    public ResponseEntity<List<Artista>> findAll(){
        return ResponseEntity
                .ok()
                .body(Arepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> findOne(@PathVariable Long id){
        return ResponseEntity
                .of(Arepository.findById(id));
    }

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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Arepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
