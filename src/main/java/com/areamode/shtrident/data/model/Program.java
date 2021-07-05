package com.areamode.shtrident.data.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "program")
@Data
public class Program {

    @Id
    private String id;

    private Integer programId;

    private String programGenre;
    private String gameGenre;

    private String title;
    private String titleKana;

    private String subTitle;
    private String subTitleKana;

    private String episodeTotal;
    private String audienceTarget;
    private String startDate;

    private String checksum;

}
