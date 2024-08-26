package com.huntercodexs.demo.system.hardsys.dto;

import java.util.List;

public class Help4DevsAudioDto {

    private final List<String> audio;

    public Help4DevsAudioDto(List<String> audio) {
        this.audio = audio;
    }

    public Help4DevsAudioDto builder() {
        return this;
    }

}
