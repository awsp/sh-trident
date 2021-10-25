package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.service.AnisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/query")
@RequiredArgsConstructor
public class QueryController {

    private final AnisonService anisonService;

    @GetMapping("program")
    public List<?> program() {
        return anisonService.getProgramLabels("2021-09-01");
    }

    @GetMapping("anison")
    public ResponseEntity<?> anison() {
        return ResponseEntity.ok("anison");
    }
}