package com.areamode.shtrident.service.task;

import com.areamode.shtrident.config.AnisonConfig;
import com.areamode.shtrident.data.model.Anison;
import com.areamode.shtrident.data.repo.AnisonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnisonIndexService extends IndexingService<Anison> {
    private final AnisonConfig config;
    private final AnisonRepository anisonRepository;

    @Override
    public void run() {
        Path path = Paths.get(config.getPath());
        resetCounter();

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            boolean headerShipped = false;
            String line;
            while ((line = br.readLine()) != null) {
                if (!headerShipped) {
                    headerShipped = true;
                    continue;
                }

                line = line.replaceAll("\"", "");
                String[] values = line.split(COMMA_DELIMITER);
                Anison anison = Anison.builder()
                        .programId((values[config.getProgramIdIndex()]))
                        .programGenre((values[config.getProgramGenreIndex()]))
                        .programName((values[config.getProgramNameIndex()]))
                        .description((values[config.getDescriptionIndex()]))
                        .descriptionSort((values[config.getDescriptionSortIndex()]))
                        .anisonId((values[config.getAnisonIdIndex()]))
                        .title((values[config.getTitleIndex()]))
                        .songBy((values[config.getSongByIndex()]))
                        .build();

                anison.setChecksum(getHash(anison));
                logProgress(commit());

                if (exists(anison)) {
                    continue;
                }
                anisonRepository.save(anison);
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    private void logProgress(int count) {
        if (count > 0 && count % config.getReportPerLine() == 0) {
            log.info("Processed (Anison): " + count);
        }
    }

    @Override
    String getHash(Anison anison) {
        return DigestUtils.md5Hex(anison.getProgramGenre() + SEPARATOR +
                anison.getAnisonId() + SEPARATOR +
                anison.getProgramName() + SEPARATOR +
                anison.getProgramGenre() + SEPARATOR +
                anison.getDescription() + SEPARATOR +
                anison.getDescriptionSort() + SEPARATOR +
                anison.getTitle() + SEPARATOR +
                anison.getSongBy());
    }

    @Override
    boolean exists(Anison anison) {
        return anisonRepository.existsByChecksum(anison.getChecksum());
    }

}