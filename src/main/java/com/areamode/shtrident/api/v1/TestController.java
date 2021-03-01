package com.areamode.shtrident.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("")
    public ResponseEntity<?> sampleData() {
        String time = LocalDateTime.now().toString();
        return ResponseEntity.ok("{\"timestamp\": \"" + time + "\"}");
    }
}
