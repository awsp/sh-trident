package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Work;

import java.util.List;
import java.util.Optional;

public interface WorkService {
    List<Work> getWorks();

    Optional<Work> getWork(Long workId);

    Work saveWork(Work work);

    void deleteWork(Long workId);
}
