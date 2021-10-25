package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Subscription;
import com.areamode.shtrident.payload.request.FeedRequest;
import com.areamode.shtrident.service.task.AnisonIndexService;
import com.areamode.shtrident.service.task.ProgramIndexingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulingService {
    private final ExecutorService executor = Executors.newFixedThreadPool(5);
    private final Map<String, Future<?>> executorMap = new TreeMap<>();

    private final FeedSubscriptionService feedSubscriptionService;
    private final ProgramIndexingService programIndexingService;
    private final AnisonIndexService anisonIndexService;

    @Scheduled(cron = "${cron.expression}")
    public void doJob() {
        log.info("Starting cron job");

        List<Subscription> subscriptions = feedSubscriptionService.getSubscriptions();

        for (Subscription subscription : subscriptions) {
            log.info("Parsing: " + subscription.getUrl());
            feedSubscriptionService.saveFeed(FeedRequest.builder().url(subscription.getUrl()).build());
        }
        log.info("Cron job completed");
    }

    /**
     * Index program.csv, can be running once a date or by system controller
     * /api/v1/system/index-program
     *
     * @return uuid associated to this task
     */
    public String doIndexProgram() {
        Future<?> task = executor.submit(programIndexingService);
        return execute(task);
    }

    public String doIndexAnison() {
        Future<?> task = executor.submit(anisonIndexService);
        return execute(task);
    }

    /**
     * Cancelling a task using UUID
     * @param uuid UUID of a task to be cancelled.
     * @return if cancel is success or not
     */
    public boolean doCancel(final String uuid) {
        Future<?> task = executorMap.get(uuid);
        if (task != null) {
            task.cancel(true);
            return task.isCancelled();
        }
        return false;
    }

    /**
     * Get status of a task by UUID
     * @param uuid UUID of a test to be reported.
     * @return if task is done or not
     */
    public boolean status(final String uuid) {
        Future<?> task = executorMap.get(uuid);
        if (task != null) {
            return task.isDone();
        }
        return true;
    }

    /**
     * Execute a task and return a UUID for this task
     * @param task Task to be executed
     * @return UUID for a task
     */
    private String execute(Future<?> task) {
        String uuid = UUID.randomUUID().toString();
        executorMap.put(uuid, task);
        return uuid;
    }

}