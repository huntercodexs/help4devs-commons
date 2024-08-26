package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsBridgeDto {

    private final List<String> bridge;

    public Help4DevsBridgeDto(List<String> bridge) {
        this.bridge = bridge;
    }

    public Help4DevsBridgeDto builder() {
        return this;
    }

}
