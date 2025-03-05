package com.huntercodexs.demo.services.notifier.teams.model.adaptivecard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageObject {
    private final String type = "Image";
    private final String size = "large";
    private String altText;
    private String url;
    private final String spacing = "large";
}
