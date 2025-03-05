package com.huntercodexs.demo.services.notifier.teams.model.messagecard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Targets {
    private String os;
    private String uri;
}