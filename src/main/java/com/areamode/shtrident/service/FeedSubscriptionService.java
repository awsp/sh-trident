package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Feed;
import com.areamode.shtrident.data.model.Subscription;
import com.areamode.shtrident.payload.request.FeedRequest;

import java.util.List;
import java.util.Optional;

public interface FeedSubscriptionService {
    boolean saveFeed(FeedRequest feedRequest);
    Iterable<Feed> listFeeds();
    Long getFeedCount();

    Optional<Subscription> findSubscriptionById(Long subscriptionId);
    List<Subscription> getSubscriptions();
    Subscription saveSubscription(Subscription subscription);
}