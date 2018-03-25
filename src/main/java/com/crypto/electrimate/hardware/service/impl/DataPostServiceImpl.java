package com.crypto.electrimate.hardware.service.impl;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.dto.SensorDto;
import com.crypto.electrimate.hardware.entity.Raw;
import com.crypto.electrimate.hardware.entity.Sensor;
import com.crypto.electrimate.hardware.service.DataPostService;
import com.crypto.electrimate.hardware.service.RawDataService;
import com.crypto.electrimate.hardware.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/25/18.
 */
@Component
public class DataPostServiceImpl implements DataPostService {

    @Autowired
    private RawDataService rawDataService;

    @Autowired
    private SensorService sensorService;

    @Scheduled(fixedRate = 5000)
    public void post() {
        System.out.println("POSTING");
        Collection<Sensor> sensors = sensorService.allSensors();
        Collection<SensorDto> postData = new ArrayList();
        Collection<Raw> allRaw = new ArrayList<>();
        sensors.forEach(sensor -> {
            Collection<Raw> rawCollection = rawDataService.getAllForPost(sensor);
            Collection<RawDto> rawData = new ArrayList();
            rawCollection.forEach(raw -> {
                rawData.add(raw.getDto());
            });
            postData.add(new SensorDto(sensor.getSerialNumber(), rawData));
            allRaw.addAll(rawCollection);
        });
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Collection<SensorDto>> request = new HttpEntity<>(postData);
        ResponseEntity<Object> l = restTemplate
                .exchange("http://localhost:3000/api/v1/raw-data", HttpMethod.POST, request, Object.class);
        if (l.getStatusCode().is2xxSuccessful()) {
            rawDataService.updateStatus(allRaw, true);
        }
    }

}
