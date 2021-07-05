package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.service.SchedulingService;
import lombok.RequiredArgsConstructor;
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

    }
}
