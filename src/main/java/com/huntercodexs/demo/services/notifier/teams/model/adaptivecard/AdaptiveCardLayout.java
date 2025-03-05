package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AdaptiveCardLayout {
    private final String type = "message";
    private List<Attachments> attachments;
}