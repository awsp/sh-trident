package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.FeedRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class SchedulingService {

    private final FeedService feedService;

    @Value("${scheduler.urls}")
    private final String[] urls;

    @Scheduled(cron = "${cron.expression}")
    public void doJob() {
        for (String url : urls) {
            feedService.saveFeed(FeedRequest.builder().url(url).build());
        }
        log.info("Completed");
    }
}
