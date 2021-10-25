package com.areamode.shtrident.service;

import com.areamode.shtrident.data.projection.ProgramDetailProjection;
import com.areamode.shtrident.data.projection.ProgramListProjection;
import com.areamode.shtrident.data.repo.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnisonServiceImpl implements AnisonService {

    private final ProgramRepository programRepository;


    @Override
    public List<ProgramListProjection> getProgramLabels(final String startDate) {
        return programRepository.findProgramsByStartDateAfter(startDate);
    }

    @Override
    public List<ProgramDetailProjection> allProgramsWithDetail() {
        return null;
    }
}