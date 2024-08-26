package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsBluetoothDto {

    private final List<String> bluetooth;

    public Help4DevsBluetoothDto(List<String> bluetooth) {
        this.bluetooth = bluetooth;
    }

    public Help4DevsBluetoothDto builder() {
        return this;
    }

}
