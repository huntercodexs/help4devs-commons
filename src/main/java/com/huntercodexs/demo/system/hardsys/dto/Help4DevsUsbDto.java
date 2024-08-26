package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsUsbDto {

    private final List<String> usb;

    public Help4DevsUsbDto(List<String> usb) {
        this.usb = usb;
    }

    public Help4DevsUsbDto builder() {
        return this;
    }

}
