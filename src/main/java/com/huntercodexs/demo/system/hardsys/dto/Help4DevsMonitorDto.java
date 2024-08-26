package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsMonitorDto {

    private final List<String> monitor;

    public Help4DevsMonitorDto(List<String> monitor) {
        this.monitor = monitor;
    }

    public Help4DevsMonitorDto builder() {
        return this;
    }

}
