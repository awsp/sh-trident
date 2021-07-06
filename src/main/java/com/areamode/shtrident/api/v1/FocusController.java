package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.data.model.Feed;
import com.areamode.shtrident.data.model.Focus;
import com.areamode.shtrident.service.FocusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/focus")
@RequiredArgsConstructor
@CrossOrigin("${cors.allowed-origins}")
public class FocusController {

    private final FocusService focusService;

    @PostMapping({"", "/"})
    public ResponseEntity<Focus> createFocus(@RequestBody Focus focus) {
        return ResponseEntity.ok(focusService.save(focus));
    }

    @GetMapping("/{focusId}/feeds")
    public Set<Feed> getFeeds(@PathVariable Long focusId) {
        Optional<Focus> focusOptional = focusService.findFocus(focusId);
        if (focusOptional.isPresent()) {
            return focusService.getFeeds(focusOptional.get());
        }
        return Collections.emptySet();
    }

    @DeleteMapping("/{focusId}")
    public ResponseEntity<?> deleteFocus(@PathVariable(name = "focusId") Long focusId) {
        focusService.deleteFocus(focusId);
        return ResponseEntity.accepted().body("{}");
    }
}