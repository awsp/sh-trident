package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.domain.enumeration.PagingHeaders;
import com.areamode.shtrident.domain.model.Program;
import com.areamode.shtrident.payload.response.PagingResponse;
import com.areamode.shtrident.service.AnisonService;
import lombok.RequiredArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/query")
@RequiredArgsConstructor
public class QueryController {

    private final AnisonService anisonService;

    @Transactional
    @GetMapping(value = "program", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Program>> get(
            @And({
                    @Spec(path = "title", params = "title", spec = Like.class),
                    @Spec(path = "titleKana", params = "titleKana", spec = Like.class),
                    @Spec(path = "programGenre", params = "programGenre", spec = Equal.class),
                    @Spec(path = "startDate", params = "startDate", spec = Equal.class),
                    @Spec(path = "startDate", params = {"startDateGt", "startDateLt"}, spec = Between.class)
            }) Specification<Program> spec,
            Sort sort,
            @RequestHeader HttpHeaders headers) {

        PagingResponse response = anisonService.getPrograms(spec, headers, sort);
        return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
    }

    @GetMapping(value = "song", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> song() {
        return ResponseEntity.ok("anison");
    }

    private HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));

        return headers;
    }
}