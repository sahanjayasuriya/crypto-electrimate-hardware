package com.crypto.electrimate.hardware.service.impl;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.entity.Raw;
import com.crypto.electrimate.hardware.entity.Sensor;
import com.crypto.electrimate.hardware.repository.RawRepository;
import com.crypto.electrimate.hardware.service.RawDataService;
import com.crypto.electrimate.hardware.service.SensorService;
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

    @Autowired
    private SensorService sensorService;

    @Override
    public Raw save(RawDto rawDto) {
        LOGGER.info("SAVING SINGLE");
        Assert.notNull(rawDto, "RawDto cannot be null");
        Assert.notNull(rawDto.getI(), "Current cannot be null");
        Assert.notNull(rawDto.getV(), "Voltage cannot be null");
        Assert.state(checkSensorDetails(rawDto), "Either Sensor ID or Sensor PIN should be provided");

        Sensor sensor = sensorService.findSensor(rawDto);
        if (sensor != null) {
            Raw raw = new Raw();
            raw.setCurrent(rawDto.getI());
            raw.setVoltage(rawDto.getV());
            raw.setSensor(sensor);
            raw.setDateTime(System.nanoTime());
            raw.setUploaded(!ALREADY_UPLOADED);

            return rawRepository.save(raw);
        }
        return null;
    }

    @Override
    @Async
    public void save(Collection<RawDto> rawDtos) {
        LOGGER.info("SAVING LIST: {}", rawDtos.size());
        rawDtos.forEach(rawDto -> save(rawDto));
    }

    @Override
    public Collection<Raw> getAllForPost(Sensor sensor) {
        return rawRepository.findFirst100BySensorAndUploaded(sensor, !ALREADY_UPLOADED);
    }

    @Override
    public void updateStatus(Collection<Raw> rawCollection, boolean uploaded) {
        rawCollection.forEach(raw -> {
            raw.setUploaded(uploaded);
            rawRepository.save(raw);
        });
    }

    private boolean checkSensorDetails(RawDto rawDto) {
        return rawDto.getSerialNumber() != null || rawDto.getPin() != null;
    }

}
