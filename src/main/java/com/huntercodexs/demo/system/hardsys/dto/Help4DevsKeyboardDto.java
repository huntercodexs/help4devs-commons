package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsKeyboardDto {

    private final List<String> keyboard;

    public Help4DevsKeyboardDto(List<String> keyboard) {
        this.keyboard = keyboard;
    }

    public Help4DevsKeyboardDto builder() {
        return this;
    }

}
