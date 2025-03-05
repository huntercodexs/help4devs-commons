package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodyContent {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BodyColumSet bodyColumSet;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ImageObject imageObject;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TextObject textObject;

    private BodyTitle bodyTitle;
    private BodyFactsSet bodyFactsSet;
    private BodyPayload bodyPayload;
    private BodySourcePayload bodySourcePayload;
    private BodyActionSet bodyActions;
}