package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdaptiveCardFacts {
    private String title;
    private String value;
}

