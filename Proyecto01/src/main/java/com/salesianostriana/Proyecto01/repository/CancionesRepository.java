package com.salesianostriana.Proyecto01.repository;

import com.salesianostriana.Proyecto01.model.Canciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionesRepository extends JpaRepository<Canciones, Long> {
}
