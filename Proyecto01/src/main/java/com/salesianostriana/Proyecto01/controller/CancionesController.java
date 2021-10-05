package com.salesianostriana.Proyecto01.controller;

import com.salesianostriana.Proyecto01.dto.CancionesDtoConverter;
import com.salesianostriana.Proyecto01.dto.GetCancionesDto;
import com.salesianostriana.Proyecto01.model.Canciones;
import com.salesianostriana.Proyecto01.repository.CancionesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/canciones")
public class CancionesController {

    private final CancionesRepository Crepository;
    private final CancionesDtoConverter DtoConverter;


    @GetMapping("/")
    public ResponseEntity<List<GetCancionesDto>> findAll(){
        List<Canciones> data = Crepository.findAll();
       if(data.isEmpty()){
           return ResponseEntity.notFound().build();
       }else{
           List<GetCancionesDto> resultado=
                   data.stream().map(DtoConverter::cancionesToGetCancionesDto)
                   .collect(Collectors.toList());
           return ResponseEntity.ok().body(resultado);
       }
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
