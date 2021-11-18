package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnisonRepository extends JpaRepository<Song, Long> {
    Boolean existsByChecksum(String checksum);
}