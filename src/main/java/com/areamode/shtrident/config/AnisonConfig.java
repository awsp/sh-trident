package com.areamode.shtrident.config;

import com.areamode.shtrident.data.enumeration.ProgramGenre;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;

@Configuration
public class AnisonConfig {

    @Getter
    private final int programIdIndex = 0;

    @Getter
    private final int programGenreIndex = 1;

    @Getter
    private final int programNameIndex = 2;

    @Getter
    private final int descriptionIndex = 3;

    @Getter
    private final int descriptionSortIndex = 4;

    @Getter
    private final int anisonIdIndex = 5;

    @Getter
    private final int titleIndex = 6;

    @Getter
    private final int songByIndex = 7;

    @Getter
    @Setter
    @Value("${indexing.anison.path}")
    private String path = "";

    @Getter
    @Setter
    @Value("${indexing.anison.reportPerLine}")
    private int reportPerLine = 100;

    public static EnumMap<ProgramGenre, String> programGenres= new EnumMap<>(ProgramGenre.class);

    static {
        programGenres.put(ProgramGenre.TV, "テレビアニメーション");
    }

}