package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.data.model.Feed;
import com.areamode.shtrident.payload.request.FeedRequest;
import com.areamode.shtrident.service.FeedSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedSubscriptionService feedSubscriptionService;

    // TODO: Temporary showing all feeds, remove / use Pagable instead
    @GetMapping({"", "/"})
    public Iterable<Feed> listFeed() {
        return feedSubscriptionService.listFeeds();
    }

    @PostMapping({"", "/"})
    public ResponseEntity<String> createFeed(@RequestBody FeedRequest url) {
        boolean success = feedSubscriptionService.saveFeed(url);
        return ResponseEntity.ok("{\"success\": " + success + "}");
    }

    @GetMapping("/count")
    public ResponseEntity<?> getFeedCount() {
        return ResponseEntity.ok("{\"count\": " + feedSubscriptionService.getFeedCount() + "\"}");
    }
}