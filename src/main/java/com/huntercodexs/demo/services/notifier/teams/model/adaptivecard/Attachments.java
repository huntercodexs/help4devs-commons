package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attachments {
    private final String contentType = "application/vnd.microsoft.card.adaptive";
    private Content content;
}