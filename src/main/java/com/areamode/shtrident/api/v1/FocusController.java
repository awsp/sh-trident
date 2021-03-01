package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.data.model.Focus;
import com.areamode.shtrident.service.FocusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
