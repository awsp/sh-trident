package com.areamode.shtrident.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Focus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ElementCollection
    private List<String> phrases = new ArrayList<>();

    private int counter;

    private int total;

    @Lob
    private String remarks;
}
