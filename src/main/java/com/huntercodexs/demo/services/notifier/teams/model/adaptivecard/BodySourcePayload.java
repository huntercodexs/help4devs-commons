package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodySourcePayload {
    private final String type = "TextBlock";
    private final String wrap = "true";
    private final String spacing = "Small";

    private String text;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String color;
}