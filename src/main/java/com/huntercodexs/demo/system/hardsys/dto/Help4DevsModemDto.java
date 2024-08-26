package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsModemDto {

    private final List<String> modem;

    public Help4DevsModemDto(List<String> modem) {
        this.modem = modem;
    }

    public Help4DevsModemDto builder() {
        return this;
    }

}
