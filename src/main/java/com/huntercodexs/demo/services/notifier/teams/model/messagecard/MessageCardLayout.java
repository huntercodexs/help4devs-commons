package com.huntercodexs.demo.services.notifier.teams.model.messagecard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageCardLayout {
    private final String type = "MessageCard";
    private final String context = "http://schema.org/extensions";
    private String themeColor;
    private String summary;
    private List<Sections> sections;
    private List<PotentialAction> potentialAction;
}