package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Program;
import com.areamode.shtrident.payload.response.PagingResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

public interface AnisonService {
    PagingResponse getPrograms(Specification<Program> spec, HttpHeaders headers, Sort sort);
}