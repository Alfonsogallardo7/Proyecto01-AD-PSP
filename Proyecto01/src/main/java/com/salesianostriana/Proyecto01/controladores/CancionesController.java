package com.salesianostriana.Proyecto01.controladores;

import com.salesianostriana.Proyecto01.model.Canciones;
import com.salesianostriana.Proyecto01.repository.CancionesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/canciones")
public class CancionesController {

    private final CancionesRepository Crepository;

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
