package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Feed;
import com.areamode.shtrident.data.model.Focus;
import com.areamode.shtrident.data.repo.FeedRepository;
import com.areamode.shtrident.data.repo.FocusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
@RequiredArgsConstructor
public class FocusServiceImpl implements FocusService {

    private final FocusRepository focusRepository;
    private final FeedRepository feedRepository;

    @Override
    public Focus save(Focus focus) {
        return focusRepository.save(focus);
    }

    @Override
    public Set<Feed> getFeeds(Focus focus) {
        Set<Feed> feeds = new TreeSet<>();
        for (String phrase : focus.getPhrases()) {
            List<Feed> allByTitle = feedRepository.findAllByTitle(phrase);
            feeds.addAll(allByTitle);
        }
        return feeds;
    }

    public Optional<Focus> findFocus(Long focusId) {
        return focusRepository.findById(focusId);
    }

    @Override
    public void deleteFocus(Long focusId) {
        focusRepository.deleteById(focusId);
    }

}