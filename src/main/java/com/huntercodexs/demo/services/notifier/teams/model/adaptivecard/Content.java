package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Content {
    private final String schema = "http://adaptivecards.io/schemas/adaptive-card.json";
    private final String type = "AdaptiveCard";
    private final String version = "1.4";
    private final MSTeams msteams;
    private final List<BodyContent> body;
}