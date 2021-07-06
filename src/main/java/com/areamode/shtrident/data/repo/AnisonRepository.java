package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Anison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnisonRepository extends JpaRepository<Anison, Long> {
    Boolean existsByChecksum(String checksum);
}