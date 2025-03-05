package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BodyColumSet {
    private final String type = "ColumnSet";
    private List<ColumnsSet> columns;
}
