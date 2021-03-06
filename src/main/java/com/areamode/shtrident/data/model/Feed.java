package com.areamode.shtrident.data.model;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "feed")
@Data
public class Feed implements Comparable<Feed> {
    @Id
    private String id;

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
