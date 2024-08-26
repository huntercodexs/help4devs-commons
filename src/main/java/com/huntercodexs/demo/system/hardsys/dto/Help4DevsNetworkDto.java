package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsNetworkDto {

    private final List<String> network;

    public Help4DevsNetworkDto(List<String> network) {
        this.network = network;
    }

    public Help4DevsNetworkDto builder() {
        return this;
    }

}
