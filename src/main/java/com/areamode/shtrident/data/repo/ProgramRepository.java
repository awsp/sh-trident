package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProgramRepository extends JpaRepository<Program, String>, JpaSpecificationExecutor<Program> {
    Boolean existsByChecksum(String checksum);
}