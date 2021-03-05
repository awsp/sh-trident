package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Feed;
import com.areamode.shtrident.data.model.Focus;

import java.util.Optional;
import java.util.Set;

public interface FocusService {
    Focus save(Focus focus);
    Optional<Focus> findFocus(Long focusId);
    Set<Feed> getFeeds(Focus focus);
}
