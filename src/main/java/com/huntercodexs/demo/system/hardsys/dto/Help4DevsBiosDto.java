package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsBiosDto {

    private final List<String> bios;

    public Help4DevsBiosDto(List<String> bios) {
        this.bios = bios;
    }

    public Help4DevsBiosDto builder() {
        return this;
    }

}
