package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsDiskDto {

    private final List<String> disk;

    public Help4DevsDiskDto(List<String> disk) {
        this.disk = disk;
    }

    public Help4DevsDiskDto builder() {
        return this;
    }

}
