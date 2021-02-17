package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.data.model.FeedRequest;
import com.areamode.shtrident.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/samples")
    public ResponseEntity<String> index() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("test_data.xml"));
            return ResponseEntity.ok(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("not found");
    }

    @PostMapping({"", "/"})
    public ResponseEntity<String> create(@RequestBody FeedRequest url) {
        boolean success = feedService.saveFeed(url);
        return ResponseEntity.ok("{\"success\": " + success + "}");
    }
}
