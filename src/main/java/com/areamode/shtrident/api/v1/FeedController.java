package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.data.model.Feed;
import com.areamode.shtrident.data.model.FeedRequest;
import com.areamode.shtrident.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping({"", "/"})
    public Iterable<Feed> index() {
        return feedService.getFeeds();
    }

    @PostMapping({"", "/"})
    public ResponseEntity<String> create(@RequestBody FeedRequest url) {
        boolean success = feedService.saveFeed(url);
        return ResponseEntity.ok("{\"success\": " + success + "}");
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        return ResponseEntity.ok("{\"count\": " + feedService.getFeedCount() + "\"}");
    }
}
