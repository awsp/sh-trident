package com.areamode.shtrident.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnisonConfig {

    @Getter
    private final int programIdIndex = 0;

    @Getter
    private final int programGenreIndex = 1;

    @Getter
    private final int gameGenreIndex = 2;

    @Getter
    private final int titleIndex = 3;

    @Getter
    private final int titleKanaIndex = 4;

    @Getter
    private final int subTitleIndex = 5;

    @Getter
    private final int subTitleKanaIndex = 6;

    @Getter
    private final int episodeTotalIndex = 7;

    @Getter
    private final int audienceTargetIndex = 8;

    @Getter
    private final int startDateIndex = 9;

}
