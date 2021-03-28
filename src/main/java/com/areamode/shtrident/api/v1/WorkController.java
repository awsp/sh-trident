package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.data.model.Work;
import com.areamode.shtrident.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/work")
public class WorkController {

    private final WorkService workService;

    @GetMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Work> getWorks() {
        return workService.getWorks();
    }

    @GetMapping(value = "/{workId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Work> getWork(@PathVariable(name = "workId") Long workId) {
        Optional<Work> work = workService.getWork(workId);
        return work.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = {"", "/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Work> createWork(@RequestBody Work work) {
        return ResponseEntity.ok(workService.saveWork(work));
    }
}
