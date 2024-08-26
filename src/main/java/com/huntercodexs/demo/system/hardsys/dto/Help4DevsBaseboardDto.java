package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsBaseboardDto {

    private final List<String> baseboard;

    public Help4DevsBaseboardDto(List<String> baseboard) {
        this.baseboard = baseboard;
    }

    public Help4DevsBaseboardDto builder() {
        return this;
    }

}
