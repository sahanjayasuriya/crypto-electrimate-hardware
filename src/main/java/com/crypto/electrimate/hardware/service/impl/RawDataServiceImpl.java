package com.crypto.electrimate.hardware.service.impl;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.dto.SensorDto;
import com.crypto.electrimate.hardware.entity.Raw;
import com.crypto.electrimate.hardware.repository.RawRepository;
import com.crypto.electrimate.hardware.service.RawDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Service
public class RawDataServiceImpl implements RawDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RawDataServiceImpl.class);

    private static final boolean ALREADY_UPLOADED = true;
    @Autowired
    private RawRepository rawRepository;

    @Override
    public Raw save(RawDto rawDto) {
        Assert.notNull(rawDto, "RawDto cannot be null");
        Assert.notNull(rawDto.getI(), "Current cannot be null");
        Assert.notNull(rawDto.getV(), "Voltage cannot be null");
        Assert.notNull(rawDto.getPin(), "Pin cannot be null");

        Raw raw = new Raw();
        raw.setCurrent(rawDto.getI());
        raw.setVoltage(rawDto.getV());
        raw.setPin(rawDto.getPin());
        raw.setDateTime(rawDto.getDateTime());
        raw.setTimeDiff(rawDto.getTimeDiff());
        raw.setUploaded(!ALREADY_UPLOADED);

        return rawRepository.save(raw);

    }

    @Override
    @Async
    public void save(Collection<RawDto> rawDtos) {
        rawDtos.forEach(rawDto -> save(rawDto));
    }

    @Override
    public Collection<Raw> getAllForPost(Integer pin) {
        return rawRepository.findFirst100ByPinAndUploaded(pin, !ALREADY_UPLOADED);
    }

    @Override
    public void updateStatus(Collection<Raw> rawCollection, boolean uploaded) {
        rawCollection.forEach(raw -> {
            raw.setUploaded(uploaded);
            rawRepository.save(raw);
        });
    }

    @Override
    public Collection<SensorDto> allPins() {
        return rawRepository.findAllPins();
    }


}
