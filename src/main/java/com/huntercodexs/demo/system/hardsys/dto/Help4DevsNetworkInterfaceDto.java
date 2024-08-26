package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsNetworkInterfaceDto {

    private final List<String> networkInterface;

    public Help4DevsNetworkInterfaceDto(List<String> networkInterface) {
        this.networkInterface = networkInterface;
    }

    public Help4DevsNetworkInterfaceDto builder() {
        return this;
    }

}
