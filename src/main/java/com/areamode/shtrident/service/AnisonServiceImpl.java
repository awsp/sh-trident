package com.areamode.shtrident.service;

import com.areamode.shtrident.config.AnisonConfig;
import com.areamode.shtrident.data.model.Anison;
import com.areamode.shtrident.data.model.Program;
import com.areamode.shtrident.data.repo.AnisonRepository;
import com.areamode.shtrident.data.repo.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class AnisonServiceImpl implements AnisonService {
    private final static String COMMA_DELIMITER = ",";
    private final static String SEPARATOR = "|";

    private final AnisonConfig anisonConfig;
    private final ProgramRepository programRepository;
    private final AnisonRepository anisonRepository;

    @Override
    public void saveProgram(Path path) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            boolean headerShipped = false;
            String line;
            while ((line = br.readLine()) != null) {
                if (!headerShipped) {
                    headerShipped = true;
                    continue;
                }

                String[] values = line.split(COMMA_DELIMITER);
                Program program = new Program();
                program.setProgramId(Integer.parseInt(values[anisonConfig.getProgramIdIndex()]));
                program.setProgramGenre(values[anisonConfig.getProgramGenreIndex()]);
                program.setGameGenre(values[anisonConfig.getGameGenreIndex()]);
                program.setTitle(values[anisonConfig.getTitleIndex()]);
                program.setTitleKana(values[anisonConfig.getTitleKanaIndex()]);
                program.setSubTitle(values[anisonConfig.getSubTitleIndex()]);
                program.setSubTitleKana(values[anisonConfig.getSubTitleKanaIndex()]);
                program.setEpisodeTotal(values[anisonConfig.getEpisodeTotalIndex()]);
                program.setAudienceTarget(values[anisonConfig.getAudienceTargetIndex()]);
                program.setStartDate(values[anisonConfig.getStartDateIndex()]);

                program.setChecksum(getHash(program));

                if (!exists(program)) {
                    programRepository.save(program);
                }
            }
        }
    }

    private boolean exists(Program program) {
        return programRepository.findFirstByChecksum(program.getChecksum()).isPresent();
    }

    @Override
    public String getHash(Program program) {
        return DigestUtils.md5Hex(program.getProgramId() + SEPARATOR +
                program.getProgramGenre() + SEPARATOR +
                program.getGameGenre() + SEPARATOR +
                program.getTitle() + SEPARATOR +
                program.getTitleKana() + SEPARATOR +
                program.getSubTitle() + SEPARATOR +
                program.getSubTitleKana() + SEPARATOR +
                program.getAudienceTarget() + SEPARATOR +
                program.getStartDate());
    }

    @Override
    public String getHash(Anison anison) {
        return null;
    }
}
