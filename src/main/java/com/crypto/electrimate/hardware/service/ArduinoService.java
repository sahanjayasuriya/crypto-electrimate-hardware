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
    int TIME_DIFF_INDEX = 3;

    int DATA_COUNT = 4;

    void begin();

    default Collection<RawDto> deserialize(String rawData) {
        Assert.notNull(rawData, "RawData cannot be null");
        String[] data = rawData.split("\r\n");
        List<RawDto> raws = new ArrayList<>();
        for (String dataUnit : data) {
            if (dataUnit.trim().matches("\\*(\\d+.{0,1}\\d*\\|){3}\\d+\\+")) {
                String[] reading = dataUnit.replaceAll("\\*", "").replaceAll("\\+", "").split("\\|");
                if (reading.length == DATA_COUNT) {
                    try {
//                        if ("1".equals(reading[PIN_INDEX].trim())) {
//                            String s = String.format("Voltage: %s\t\tCurrent: %s\t\tDuration: %s\t\tPin: %s", reading[VOLTAGE_INDEX], reading[CURRENT_INDEX], reading[TIME_DIFF_INDEX], reading[PIN_INDEX]);
//                            System.out.println(s);
//                        }
                        BigDecimal current = new BigDecimal(reading[CURRENT_INDEX].trim());
                        if (!current.equals(BigDecimal.ZERO)) {
                            raws.add(
                                    new RawDto(
                                            new BigDecimal(reading[VOLTAGE_INDEX].trim()),
                                            current,
                                            Long.parseLong(reading[TIME_DIFF_INDEX].trim()),
                                            Integer.parseInt(reading[PIN_INDEX].trim())
                                    )
                            );
                        }
                    } catch (NumberFormatException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        }
        return raws;
    }

}
