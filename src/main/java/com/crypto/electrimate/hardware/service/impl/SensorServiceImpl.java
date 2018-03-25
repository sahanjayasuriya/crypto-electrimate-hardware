package com.crypto.electrimate.hardware.service.impl;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.entity.Sensor;
import com.crypto.electrimate.hardware.repository.SensorRepository;
import com.crypto.electrimate.hardware.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;


    @Override
    public Sensor findSensor(RawDto rawDto) {
        Sensor sensor = null;
        if (rawDto.getSerialNumber() != null) {
            sensor = sensorRepository.findBySerialNumber(rawDto.getSerialNumber());
        } else if (rawDto.getPin() != null) {
            sensor = sensorRepository.findByPin(rawDto.getPin());
        }
        return sensor;
    }

    @Override
    public Collection<Sensor> allSensors() {
        return sensorRepository.findAll();
    }
}
