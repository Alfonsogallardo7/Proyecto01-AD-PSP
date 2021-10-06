package com.salesianostriana.Proyecto01.repository;

import com.salesianostriana.Proyecto01.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository<Cancion, Long> {
}
