package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsMouseDto {

    private final List<String> mouse;

    public Help4DevsMouseDto(List<String> mouse) {
        this.mouse = mouse;
    }

    public Help4DevsMouseDto builder() {
        return this;
    }

}
