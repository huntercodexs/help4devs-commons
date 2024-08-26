package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsSwitchDto {

    private final List<String> switcher;

    public Help4DevsSwitchDto(List<String> switcher) {
        this.switcher = switcher;
    }

    public Help4DevsSwitchDto builder() {
        return this;
    }

}
