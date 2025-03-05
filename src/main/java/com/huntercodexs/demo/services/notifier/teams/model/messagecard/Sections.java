package com.huntercodexs.demo.services.notifier.teams.model.messagecard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Sections {
    private String activityTitle;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String color;

    private String activitySubtitle;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String activityImage;

    private List<Facts> facts;
    private String markdown = "true";
}

