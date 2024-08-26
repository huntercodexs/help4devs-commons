package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsMachineDto {

    private final List<String> machine;

    public Help4DevsMachineDto(List<String> machine) {
        this.machine = machine;
    }

    public Help4DevsMachineDto builder() {
        return this;
    }
}
