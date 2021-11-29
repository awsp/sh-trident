package com.areamode.shtrident.domain.repo;

import com.areamode.shtrident.domain.model.ProgramProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramProfileRepository extends JpaRepository<ProgramProfile, Long> {
}