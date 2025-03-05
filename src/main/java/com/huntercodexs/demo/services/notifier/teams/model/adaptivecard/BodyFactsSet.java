package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BodyFactsSet {
    private final String type = "FactSet";
    private List<AdaptiveCardFacts> facts;
}