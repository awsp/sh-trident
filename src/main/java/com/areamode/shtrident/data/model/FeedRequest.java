package com.areamode.shtrident.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedRequest {
    private String url;
}
