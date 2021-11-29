package com.areamode.shtrident.api.v1;

import com.areamode.shtrident.domain.model.Episode;
import com.areamode.shtrident.domain.model.ProgramProfile;
import com.areamode.shtrident.service.AnisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
@CrossOrigin("*")
public class ProfileController {

    private final AnisonService anisonService;


    @PostMapping
    public ResponseEntity<ProgramProfile> create(@Valid @RequestBody final ProgramProfile programProfile) {
        return ResponseEntity.ok(anisonService.createProgramProfile(programProfile));
    }

    @GetMapping("/{profileId}/episodes")
    public ResponseEntity<List<Episode>> getEpisodes(@PathVariable final Long profileId) {
        return ResponseEntity.ok(anisonService.getEpisodes(profileId));
    }

    @PutMapping("/{profileId}/episodes/{totalEpisode}")
    public ResponseEntity<Void> setEpisode(@PathVariable final Long profileId,
                                           @PathVariable final Integer totalEpisode) {
        anisonService.batchCreateEpisodes(totalEpisode, profileId);
        return ResponseEntity.ok().build();
    }
}