package com.areamode.shtrident.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String programId;

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