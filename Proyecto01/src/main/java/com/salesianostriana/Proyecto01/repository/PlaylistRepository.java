package com.salesianostriana.Proyecto01.repository;

import com.salesianostriana.Proyecto01.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
