package com.areamode.shtrident.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskCancelled {
    private boolean cancelled;
}