package com.crypto.electrimate.hardware.service.impl;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.service.ArduinoService;
import com.crypto.electrimate.hardware.service.RawDataService;
import com.pi4j.io.serial.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/23/18.
 */
@Service
@Profile("pi")
public class ArduinoServiceForRPiImpl implements ArduinoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoServiceForRPiImpl.class);

    @Autowired
    private RawDataService rawDataService;

    @Value("${system.serialport}")
    private String PORT;

    @PostConstruct
    private void init() {
        begin();
    }

    /**
     * This method initializes a listener on the Serial Port and collect all raw data sent from Arduino which connected
     * through USB to the Raspberry PI. All the collected data then formatted and sent to save temporarily in the module
     * till they are sent to the server.
     */
    @Async
    @Override
    public void begin() {
        Serial serial = SerialFactory.createInstance();

        // Adding a listener to the Serial Port
        serial.addListener((SerialDataEventListener) event -> {
            try {
                String data = event.getAsciiString();
                Collection<RawDto> rawDtos = deserialize(data);
                rawDataService.save(rawDtos);
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
