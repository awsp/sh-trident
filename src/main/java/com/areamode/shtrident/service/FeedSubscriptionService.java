package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Feed;
import com.areamode.shtrident.data.model.FeedRequest;
import com.areamode.shtrident.data.model.Subscription;

import java.util.List;

public interface FeedSubscriptionService {
    boolean saveFeed(FeedRequest feedRequest);
    Iterable<Feed> listFeeds();
    Long getFeedCount();

    List<Subscription> getSubscriptions();
    Subscription saveSubscription(Subscription subscription);
}
