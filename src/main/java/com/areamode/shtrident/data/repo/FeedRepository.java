package com.areamode.shtrident.data.repo;

import com.areamode.shtrident.data.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findFirstByChecksum(String checksum);

    List<Feed> findAllByTitle(String title);
}