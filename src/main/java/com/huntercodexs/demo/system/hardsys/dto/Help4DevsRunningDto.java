package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsRunningDto {

    private final List<String> running;

    public Help4DevsRunningDto(List<String> running) {
        this.running = running;
    }

    public Help4DevsRunningDto builder() {
        return this;
    }

}
