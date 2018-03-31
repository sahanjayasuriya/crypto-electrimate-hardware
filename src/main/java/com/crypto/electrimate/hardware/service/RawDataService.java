package com.crypto.electrimate.hardware.service;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.dto.SensorDto;
import com.crypto.electrimate.hardware.entity.Raw;

import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
public interface RawDataService {

    Raw save(RawDto rawDto);

    void save(Collection<RawDto> rawDtos);

    Collection<Raw> getAllForPost(Integer pin);

    void updateStatus(Collection<Raw> rawCollection, boolean uploaded);

    Collection<SensorDto> allPins();
}
