package com.areamode.shtrident.domain.repo;

import com.areamode.shtrident.domain.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnisonRepository extends JpaRepository<Song, Long> {
    Boolean existsByChecksum(String checksum);
}