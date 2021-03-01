package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Feed;
import com.areamode.shtrident.data.model.FeedRequest;

public interface FeedService {
    boolean saveFeed(FeedRequest url);
    Iterable<Feed> getFeeds();
    Long getFeedCount();
}
