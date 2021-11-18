package com.areamode.shtrident.service;

import com.areamode.shtrident.data.enumeration.PagingHeaders;
import com.areamode.shtrident.data.model.Program;
import com.areamode.shtrident.data.repo.ProgramRepository;
import com.areamode.shtrident.payload.response.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnisonServiceImpl implements AnisonService {

    private final ProgramRepository programRepository;


    @Override
    public PagingResponse getPrograms(Specification<Program> spec, HttpHeaders headers, Sort sort) {
        return getPrograms(spec, buildPageRequest(headers, sort));
    }

    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        List<String> pageNumberHeaders = headers.get(PagingHeaders.PAGE_NUMBER.getName());
        List<String> pageSizeHeaders = headers.get(PagingHeaders.PAGE_SIZE.getName());
        int page = 0;
        int size = 10;

        if (pageNumberHeaders != null && pageSizeHeaders != null && !pageNumberHeaders.isEmpty() && !pageSizeHeaders.isEmpty())  {
            page = Integer.parseInt(pageNumberHeaders.get(0));
            size = Integer.parseInt(pageSizeHeaders.get(0));
        }
        return PageRequest.of(page, size, sort);
    }

    private PagingResponse getPrograms(Specification<Program> spec, Pageable pageable) {
        Page<Program> page = programRepository.findAll(spec, pageable);
        List<Program> programs = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(),
                (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), programs);
    }

    private List<Program> getPrograms(Specification<Program> spec, Sort sort) {
        return programRepository.findAll(spec, sort);
    }

    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }
}