package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsUnknownDto {

    private final List<String> unknown;

    public Help4DevsUnknownDto(List<String> unknown) {
        this.unknown = unknown;
    }

    public Help4DevsUnknownDto builder() {
        return this;
    }

}
