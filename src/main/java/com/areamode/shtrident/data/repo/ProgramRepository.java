package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, String> {
    Boolean existsByChecksum(String checksum);
}