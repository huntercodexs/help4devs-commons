package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TextObject {
    private final String type = "TextBlock";
    private String text;
    private String wrap;
    private String size;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String color;
}
