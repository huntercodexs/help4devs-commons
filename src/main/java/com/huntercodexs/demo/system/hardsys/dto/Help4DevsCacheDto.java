package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsCacheDto {

    private final List<String> cache;

    public Help4DevsCacheDto(List<String> cache) {
        this.cache = cache;
    }

    public Help4DevsCacheDto builder() {
        return this;
    }

}
