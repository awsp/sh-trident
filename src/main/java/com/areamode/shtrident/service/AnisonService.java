package com.areamode.shtrident.service;

import com.areamode.shtrident.data.projection.ProgramDetailProjection;
import com.areamode.shtrident.data.projection.ProgramListProjection;

import java.util.List;

public interface AnisonService {
    List<ProgramListProjection> getProgramLabels(String startDate);
    List<ProgramDetailProjection> allProgramsWithDetail();
}