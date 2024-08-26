package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsConnectorDto {

    private final List<String> connector;

    public Help4DevsConnectorDto(List<String> connector) {
        this.connector = connector;
    }

    public Help4DevsConnectorDto builder() {
        return this;
    }

}
