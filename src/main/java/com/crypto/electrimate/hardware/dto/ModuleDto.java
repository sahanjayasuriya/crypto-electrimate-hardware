package com.crypto.electrimate.hardware.dto;

import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/28/18.
 */
public class ModuleDto {

    private final Collection<SensorDto> data;
    private final String moduleSerialNumber;

    public ModuleDto(String moduleSerialNumber, Collection<SensorDto> data) {
        this.moduleSerialNumber = moduleSerialNumber;
        this.data = data;
    }

    public Collection<SensorDto> getData() {
        return data;
    }

    public String getModuleSerialNumber() {
        return moduleSerialNumber;
    }
}
