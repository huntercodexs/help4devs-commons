package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Actions {
    private final String type = "Action.OpenUrl";
    private String title;
    private String url;
}