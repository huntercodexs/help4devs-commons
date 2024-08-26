package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsMemoryDto {

    private final List<String> memory;

    public Help4DevsMemoryDto(List<String> memory) {
        this.memory = memory;
    }

    public Help4DevsMemoryDto builder() {
        return this;
    }

}
