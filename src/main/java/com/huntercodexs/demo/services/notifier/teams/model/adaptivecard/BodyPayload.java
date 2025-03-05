package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodyPayload {
    private final String type = "TextBlock";
    private final String text = "**Payload:**";
    private final String weight = "Bolder";

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String color;
}