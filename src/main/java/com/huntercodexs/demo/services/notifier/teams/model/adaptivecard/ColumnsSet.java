package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ColumnsSet {
    private final String type = "Column";
    private List<ItemsObject> items;
    private String width;
}
