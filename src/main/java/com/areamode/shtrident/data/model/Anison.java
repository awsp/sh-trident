package com.areamode.shtrident.data.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "anison")
@Data
public class Anison {

    @Id
    private String id;

    private Integer programId;
    private Integer anisonId;

    private String programGenre;
    private String programName;
    private String description;
    private String descriptionSort;

    private String title;
    private String songBy;

    private String checksum;
}
