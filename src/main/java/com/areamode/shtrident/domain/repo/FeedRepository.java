package com.areamode.shtrident.domain.repo;

import com.areamode.shtrident.domain.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findFirstByChecksum(String checksum);

    List<Feed> findAllByTitle(String title);
}