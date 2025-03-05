package com.huntercodexs.demo.services.notifier.teams.model.messagecard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Facts {
    private String name;
    private String value;
}

