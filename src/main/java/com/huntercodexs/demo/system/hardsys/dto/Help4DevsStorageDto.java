package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsStorageDto {

    private final List<String> storage;

    public Help4DevsStorageDto(List<String> storage) {
        this.storage = storage;
    }

    public Help4DevsStorageDto builder() {
        return this;
    }

}
