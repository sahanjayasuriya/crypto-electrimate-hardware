package com.crypto.electrimate.hardware.service.impl;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.service.ArduinoService;
import com.crypto.electrimate.hardware.service.RawDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4j.io.serial.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Sahan Ranasinghe on 3/23/18.
 */
@Service
public class ArduinoServiceImpl implements ArduinoService {

    private static final String PORT = "/dev/ttyUSB0";
    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoServiceImpl.class);

    @Autowired
    private RawDataService rawDataService;


    @Override
    @Async
    public void testAsync() {
        Serial serial = SerialFactory.createInstance();


        serial.addListener((SerialDataEventListener) event -> {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String data = event.getAsciiString();
                if (data != null) {
                    String rawObjects[] = data.split("\n");
                    for (String rawObject : rawObjects) {
                        rawObject = rawObject.trim();
                        if (rawObject.trim().length() > 0) {
                            RawDto raw = objectMapper.readValue(rawObject.trim(), RawDto.class);
                            rawDataService.save(raw);
                        }
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });

        try {
            // create serial config object
            SerialConfig config = new SerialConfig();

            // set default serial settings (device, baud rate, flow control, etc)
            //
            // by default, use the DEFAULT com port on the Raspberry Pi (exposed on GPIO header)
            // NOTE: this utility method will determine the default serial port for the
            //       detected platform and board/model.  For all Raspberry Pi models
            //       except the 3B, it will return "/dev/ttyAMA0".  For Raspberry Pi
            //       model 3B may return "/dev/ttyS0" or "/dev/ttyAMA0" depending on
            //       environment configuration.
            config.device(PORT)
                    .baud(Baud._9600)
                    .dataBits(DataBits._8)
                    .parity(Parity.NONE)
                    .stopBits(StopBits._1)
                    .flowControl(FlowControl.NONE);

            // open the default serial device/port with the configuration settings
            serial.open(config);

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
