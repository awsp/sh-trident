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
public class Anison {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String programId;
    private String anisonId;

    private String programGenre;
    private String programName;
    private String description;
    private String descriptionSort;

    private String title;
    private String songBy;

    private String checksum;
}