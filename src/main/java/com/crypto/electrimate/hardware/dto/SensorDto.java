package com.crypto.electrimate.hardware.dto;

import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/26/18.
 */
public class SensorDto {

    private Collection<RawDto> raw;
    private Integer pin;

    public SensorDto() {
    }

    public SensorDto(Integer pin) {
        this.pin = pin;
    }

    public SensorDto(Integer pin, Collection<RawDto> raw) {
        this.pin = pin;
        this.raw = raw;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Collection<RawDto> getRaw() {
        return raw;
    }

    public void setRaw(Collection<RawDto> raw) {
        this.raw = raw;
    }
}
