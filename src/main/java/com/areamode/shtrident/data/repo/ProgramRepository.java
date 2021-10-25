package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Program;
import com.areamode.shtrident.data.projection.ProgramListProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, String> {
    Boolean existsByChecksum(String checksum);

    List<ProgramListProjection> findProgramsByStartDateAfter(String startDate);
}