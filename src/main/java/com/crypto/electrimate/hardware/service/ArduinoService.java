package com.crypto.electrimate.hardware.service;

import com.crypto.electrimate.hardware.dto.RawDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sahan Ranasinghe on 3/23/18.
 */
public interface ArduinoService {

    Logger LOGGER = LoggerFactory.getLogger(ArduinoService.class);

    int VOLTAGE_INDEX = 0;
    int CURRENT_INDEX = 1;
    int PIN_INDEX = 2;

    int DATA_COUNT = 3;

    void begin();

    default Collection<RawDto> deserialize(String rawData) {
        Assert.notNull(rawData, "RawData cannot be null");
        String[] data = rawData.split("\r\n");
        List<RawDto> raws = new ArrayList<>();
        for (String dataUnit : data) {
            if (dataUnit.trim().matches("\\*(\\d+.\\d+\\|){2}\\d+")) {
                String[] reading = dataUnit.replaceAll("\\*", "").split("\\|");
                if (reading.length == DATA_COUNT) {
                    try {
                        raws.add(new RawDto(new BigDecimal(reading[VOLTAGE_INDEX]),
                                new BigDecimal(reading[CURRENT_INDEX]),
                                Integer.parseInt(reading[PIN_INDEX])));
                    } catch (NumberFormatException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        }
        return raws;
    }

}
