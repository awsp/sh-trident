package com.areamode.shtrident.service;

import com.areamode.shtrident.domain.model.Episode;
import com.areamode.shtrident.domain.model.Program;
import com.areamode.shtrident.domain.model.ProgramProfile;
import com.areamode.shtrident.payload.response.PagingResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface AnisonService {
    PagingResponse getPrograms(Specification<Program> spec, HttpHeaders headers, Sort sort);

    ProgramProfile createProgramProfile(ProgramProfile programProfile);

    void batchCreateEpisodes(Integer totalEpisode, Long programProfileId);

    List<Episode> getEpisodes(Long profileId);
}