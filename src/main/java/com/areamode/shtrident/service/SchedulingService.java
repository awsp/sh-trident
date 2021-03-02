package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.FeedRequest;
import com.areamode.shtrident.data.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SchedulingService {

    private final FeedSubscriptionService feedSubscriptionService;

    @Scheduled(cron = "${cron.expression}")
    public void doJob() {
        log.info("Starting cron job");

        List<Subscription> subscriptions = feedSubscriptionService.getSubscriptions();

        for (Subscription subscription : subscriptions) {
            feedSubscriptionService.saveFeed(FeedRequest.builder().url(subscription.getUrl()).build());
        }
        log.info("Cron job completed");
    }
}
