package com.areamode.shtrident.service.task;

import com.areamode.shtrident.config.ProgramConfig;
import com.areamode.shtrident.domain.model.Program;
import com.areamode.shtrident.domain.repo.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramIndexingService extends IndexingService<Program> {
    private final ProgramConfig config;
    private final ProgramRepository programRepository;

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
                Program program = Program.builder()
                        .programId((values[config.getProgramIdIndex()]))
                        .programGenre((values[config.getProgramGenreIndex()]))
                        .gameGenre((values[config.getGameGenreIndex()]))
                        .title((values[config.getTitleIndex()]))
                        .titleKana((values[config.getTitleKanaIndex()]))
                        .subTitle((values[config.getSubTitleIndex()]))
                        .subTitleKana((values[config.getTitleKanaIndex()]))
                        .episodeTotal((values[config.getEpisodeTotalIndex()]))
                        .audienceTarget((values[config.getAudienceTargetIndex()]))
                        .startDate((values[config.getStartDateIndex()]))
                        .build();
                program.setChecksum(getHash(program));
                logProgress(commit());

                if (exists(program)) {
                    continue;
                }

                programRepository.save(program);
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    private void logProgress(int count) {
        if (count > 0 && count % config.getReportPerLine() == 0) {
            log.info("Processed (Program): " + count);
        }
    }

    @Override
    String getHash(Program program) {
        return DigestUtils.md5DigestAsHex((program.getProgramId() + SEPARATOR +
                program.getProgramGenre() + SEPARATOR +
                program.getGameGenre() + SEPARATOR +
                program.getTitle() + SEPARATOR +
                program.getTitleKana() + SEPARATOR +
                program.getSubTitle() + SEPARATOR +
                program.getSubTitleKana() + SEPARATOR +
                program.getAudienceTarget() + SEPARATOR +
                program.getStartDate()).getBytes());
    }

    @Override
    boolean exists(Program program) {
        return programRepository.existsByChecksum(program.getChecksum());
    }
}