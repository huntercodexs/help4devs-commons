package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsPartitionDto {

    private final List<String> partition;

    public Help4DevsPartitionDto(List<String> partition) {
        this.partition = partition;
    }

    public Help4DevsPartitionDto builder() {
        return this;
    }

}
