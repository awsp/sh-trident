package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.payload.response.TaskAccepted;
import com.areamode.shtrident.payload.response.TaskCancelled;
import com.areamode.shtrident.service.SchedulingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/system")
public class SystemController {

    private final SchedulingService schedulingService;

    @PostMapping("/execute-scheduler")
    public void executeJob() {
        schedulingService.doJob();
    }

    @PostMapping("/open-dir")
    public void openDirectory() {
        // TODO: implement this
    }

    @PostMapping("/index-anison")
    public ResponseEntity<TaskAccepted> indexAnisonExecutor() {
        String uuid = schedulingService.doIndexAnison();
        return ResponseEntity.ok(TaskAccepted.builder().uuid(uuid).build());
    }

    @PostMapping("/index-program")
    public ResponseEntity<TaskAccepted> indexProgramExecutor() {
        String uuid = schedulingService.doIndexProgram();
        return ResponseEntity.ok(TaskAccepted.builder().uuid(uuid).build());
    }

    @GetMapping("/cancel/{uuid}")
    public ResponseEntity<TaskCancelled> cancelExecutor(@PathVariable String uuid) {
        boolean cancelled = schedulingService.doCancel(uuid);
        return ResponseEntity.ok(TaskCancelled.builder().cancelled(cancelled).build());
    }

    @GetMapping("/status/{uuid}")
    public ResponseEntity<?> statusExecutor(@PathVariable String uuid) {
        boolean status = schedulingService.status(uuid);
        return ResponseEntity.ok(status);
    }
}