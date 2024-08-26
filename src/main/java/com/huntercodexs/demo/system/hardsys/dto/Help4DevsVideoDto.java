package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsVideoDto {

    private final List<String> video;

    public Help4DevsVideoDto(List<String> video) {
        this.video = video;
    }

    public Help4DevsVideoDto builder() {
        return this;
    }

}
