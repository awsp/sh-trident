package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Feed;
import com.areamode.shtrident.data.model.FeedRequest;
import com.areamode.shtrident.data.model.Focus;
import com.areamode.shtrident.data.model.Subscription;
import com.areamode.shtrident.data.repo.FeedRepository;
import com.areamode.shtrident.data.repo.SubscriptionRepository;
import com.areamode.shtrident.exception.FeedHashException;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedSubscriptionServiceImpl implements FeedSubscriptionService {

    private final FeedRepository feedRepository;
    private final SubscriptionRepository subscriptionRepository;

    public Iterable<Feed> listFeeds() {
        return feedRepository.findAll();
    }

    @Override
    public Long getFeedCount() {
        return feedRepository.count();
    }

    @Override
    public List<Subscription> getSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean saveFeed(FeedRequest feedRequest) {
        try {
            URL source = new URL(feedRequest.getUrl());
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed syndFeed = input.build(new XmlReader(source));

            Iterator<SyndEntry> iterator = syndFeed.getEntries().iterator();
            List<Feed> feeds = new ArrayList<>();
            while (iterator.hasNext()) {
                SyndEntry syndEntry = iterator.next();
                Feed feed = new Feed();

                if (syndEntry.getEnclosures().size() == 0) {
                    continue;
                }
                String enclosureUrl = ((SyndEnclosure) syndEntry.getEnclosures().get(0)).getUrl();
                feed.setEnclosure(enclosureUrl);
                feed.setLink(syndEntry.getLink());
                feed.setTitle(syndEntry.getTitle());
                feed.setPubDate(syndEntry.getPublishedDate().toString());

                try {
                    feed.setChecksum(getHash(feed));
                    if (!exists(feed)) {
                        feeds.add(feed);
                    }
                } catch (FeedHashException fhe) {
                    log.error(fhe.getLocalizedMessage());
                }
            }
            this.feedRepository.saveAll(feeds);
            return true;

        } catch (FeedException | IOException e) {
            log.error(e.getLocalizedMessage());
        }

        return false;
    }

    private boolean exists(Feed feed) {
        return this.feedRepository.findFirstByChecksum(feed.getChecksum()).isPresent();
    }

    private String getHash(Feed feed) throws FeedHashException {
        if (feed.getEnclosure().isEmpty() || feed.getPubDate().isEmpty() || feed.getLink().isEmpty()) {
            throw new FeedHashException("Unable to generate hash");
        }
        return DigestUtils.md5Hex(feed.getPubDate() + "|" + feed.getLink() + "|" + feed.getEnclosure());
    }
}
