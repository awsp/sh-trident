package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Anison;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface AnisonRepository extends ElasticsearchRepository<Anison, String> {
    Optional<Anison> findFirstByChecksum(String checksum);
}
