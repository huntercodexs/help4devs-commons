package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BodyActionSet {
    private final String type = "ActionSet";
    private List<Actions> actions;
}