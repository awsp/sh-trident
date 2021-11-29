package com.areamode.shtrident.domain.repo;

import com.areamode.shtrident.domain.model.Episode;
import com.areamode.shtrident.domain.model.ProgramProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> getEpisodesByProgramProfileOrderByEpisodeOrderDesc(ProgramProfile programProfile);
}