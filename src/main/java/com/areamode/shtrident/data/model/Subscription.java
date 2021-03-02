package com.areamode.shtrident.data.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    @Lob
    private String remarks;
}
