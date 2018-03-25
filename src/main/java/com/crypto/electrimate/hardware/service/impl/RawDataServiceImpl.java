package com.crypto.electrimate.hardware.service.impl;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.entity.Raw;
import com.crypto.electrimate.hardware.entity.Sensor;
import com.crypto.electrimate.hardware.repository.RawRepository;
import com.crypto.electrimate.hardware.service.RawDataService;
import com.crypto.electrimate.hardware.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Service
public class RawDataServiceImpl implements RawDataService {

    @Autowired
    private RawRepository rawRepository;

    @Autowired
    private SensorService sensorService;

    @Override
    @Async
    public Raw save(RawDto rawDto) {
        Assert.notNull(rawDto, "RawDto cannot be null");
        Assert.notNull(rawDto.getI(), "Current cannot be null");
        Assert.notNull(rawDto.getV(), "Voltage cannot be null");
        Assert.state(checkSensorDetails(rawDto), "Either Sensor ID or Sensor PIN should be provided");

        Sensor sensor = sensorService.findSensor(rawDto);

        Raw raw = new Raw();
        raw.setCurrent(rawDto.getI());
        raw.setVoltage(rawDto.getV());
        raw.setSensor(sensor);
        raw.setDateTime(System.nanoTime());

        return rawRepository.save(raw);
    }

    private boolean checkSensorDetails(RawDto rawDto) {
        return rawDto.getSensorId() != null || rawDto.getPin() != null;
    }
}
