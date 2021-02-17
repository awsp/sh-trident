package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Feed;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface FeedRepository extends ElasticsearchRepository<Feed, String> {
    Optional<Feed> findFirstByChecksum(String checksum);
}
