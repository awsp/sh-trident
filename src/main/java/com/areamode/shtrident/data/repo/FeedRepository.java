package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Feed;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends ElasticsearchRepository<Feed, String> {
    Optional<Feed> findFirstByChecksum(String checksum);

    //https://docs.spring.io/spring-data/elasticsearch/docs/4.1.3/reference/html/#repository-query-keywords
    List<Feed> findAllByTitle(String title);
}
