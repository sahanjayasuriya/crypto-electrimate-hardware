package com.crypto.electrimate.hardware.service.impl;

import com.crypto.electrimate.hardware.dto.ModuleDto;
import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.dto.SensorDto;
import com.crypto.electrimate.hardware.entity.Raw;
import com.crypto.electrimate.hardware.service.DataPostService;
import com.crypto.electrimate.hardware.service.RawDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/25/18.
 */
@Component
public class DataPostServiceImpl implements DataPostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataPostServiceImpl.class);

    @Autowired
    private RawDataService rawDataService;

    @Value("${system.module.serial}")
    private String moduleSerialNumber;

    @Scheduled(fixedRate = 5000)
    public void post() {
        Collection<SensorDto> postData = rawDataService.allPins();
        Collection<Raw> allRaw = new ArrayList<>();
        postData.forEach(sensor -> {
            Collection<Raw> rawCollection = rawDataService.getAllForPost(sensor.getPin());
            Collection<RawDto> rawData = new ArrayList();
            rawCollection.forEach(raw -> {
                rawData.add(raw.getDto());
            });
            sensor.setRaw(rawData);
            allRaw.addAll(rawCollection);
        });
        // POST is done only when data availables
        if (allRaw.size() > 0) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                ModuleDto moduleDto = new ModuleDto(moduleSerialNumber, postData);
                HttpEntity<ModuleDto> request = new HttpEntity<>(moduleDto);
                ResponseEntity<Object> l = restTemplate
                        .exchange("http://localhost:3000/api/v1/raw-data", HttpMethod.POST, request, Object.class);
                if (l.getStatusCode().is2xxSuccessful()) {
                    rawDataService.updateStatus(allRaw, true);
                }
            } catch (RestClientException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

    }

}
