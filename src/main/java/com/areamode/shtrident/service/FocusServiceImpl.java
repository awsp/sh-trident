package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Focus;
import com.areamode.shtrident.data.repo.FocusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FocusServiceImpl implements FocusService {

    private final FocusRepository focusRepository;

    @Override
    public Focus save(Focus focus) {
        return focusRepository.save(focus);
    }

}
