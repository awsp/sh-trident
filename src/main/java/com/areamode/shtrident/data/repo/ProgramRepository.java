package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Program;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ProgramRepository extends ElasticsearchRepository<Program, String> {
    Optional<Program> findFirstByChecksum(String checksum);
}
