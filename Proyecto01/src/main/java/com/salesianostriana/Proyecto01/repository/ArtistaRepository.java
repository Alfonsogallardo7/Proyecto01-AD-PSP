package com.salesianostriana.Proyecto01.repository;

import com.salesianostriana.Proyecto01.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
