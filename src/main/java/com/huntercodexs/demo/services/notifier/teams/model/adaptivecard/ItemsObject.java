package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemsObject {
    private String type;
    private String style;
    private String size;
    private String altText;
    private String url;
    private String text;
    private String weight;
    private String spacing;
    private String wrap;
    private String isSubtle;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String color;
}
