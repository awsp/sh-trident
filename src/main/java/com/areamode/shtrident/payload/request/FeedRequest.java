package com.areamode.shtrident.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedRequest {
    private String url;
}