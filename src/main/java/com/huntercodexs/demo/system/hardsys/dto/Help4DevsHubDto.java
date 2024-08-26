package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsHubDto {

    private final List<String> hub;

    public Help4DevsHubDto(List<String> hub) {
        this.hub = hub;
    }

    public Help4DevsHubDto builder() {
        return this;
    }

}
