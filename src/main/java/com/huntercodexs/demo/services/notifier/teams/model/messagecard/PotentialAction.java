package com.huntercodexs.demo.services.notifier.teams.model.messagecard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PotentialAction {
    private final String type = "OpenUri";
    private String name;
    private List<Targets> targets;
}

