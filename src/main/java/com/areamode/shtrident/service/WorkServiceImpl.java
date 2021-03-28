package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Work;
import com.areamode.shtrident.data.repo.WorkMetaRepository;
import com.areamode.shtrident.data.repo.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;
    private final WorkMetaRepository workMetaRepository;

    public List<Work> getWorks() {
        return workRepository.findAll();
    }

    @Override
    public Optional<Work> getWork(Long workId) {
        return workRepository.findById(workId);
    }

    @Override
    public Work saveWork(Work work) {
        return workRepository.save(work);
    }

    @Override
    public void deleteWork(Long workId) {
        workRepository.deleteById(workId);
    }

}
