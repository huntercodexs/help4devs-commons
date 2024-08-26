package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsGraphicsDto {

    private final List<String> graphics;

    public Help4DevsGraphicsDto(List<String> graphics) {
        this.graphics = graphics;
    }

    public Help4DevsGraphicsDto builder() {
        return this;
    }

}
