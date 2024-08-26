package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsSlotsDto {

    private final List<String> slots;

    public Help4DevsSlotsDto(List<String> slots) {
        this.slots = slots;
    }

    public Help4DevsSlotsDto builder() {
        return this;
    }

}
