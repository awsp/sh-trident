package com.areamode.shtrident.data.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Feed implements Comparable<Feed> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String link;
    private String enclosure;
    private String pubDate;
    private String checksum;

    @Override
    public int compareTo(Feed o) {
        return this.title.compareTo(o.getTitle());
    }
}