package com.crypto.electrimate.hardware.dto;

import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/26/18.
 */
public class SensorDto {

    private String serialNumber;
    private Collection<RawDto> raw;

    public SensorDto(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public SensorDto(String serialNumber, Collection<RawDto> raw) {
        this.serialNumber = serialNumber;
        this.raw = raw;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Collection<RawDto> getRaw() {
        return raw;
    }

    public void setRaw(Collection<RawDto> raw) {
        this.raw = raw;
    }
}
