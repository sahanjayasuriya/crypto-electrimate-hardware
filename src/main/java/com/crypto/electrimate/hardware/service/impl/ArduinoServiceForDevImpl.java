package com.crypto.electrimate.hardware.service.impl;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.service.ArduinoService;
import com.crypto.electrimate.hardware.service.RawDataService;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/23/18.
 */
@Service
@Profile("dev")
public class ArduinoServiceForDevImpl implements ArduinoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoServiceForDevImpl.class);

    @Autowired
    private RawDataService rawDataService;

    @Value("${system.serialport}")
    private String PORT;

    @PostConstruct
    private void init() {
        begin();
    }

    private String rawData;

    /**
     * This method initializes a listener on the Serial Port and collect all raw data sent from Arduino which connected
     * through USB to the PC. All the collected data then formatted and sent to save temporarily in the module
     * till they are sent to the server.
     */
    @Async
    @Override
    public void begin() {
        try {
            SerialPort comPort = SerialPort.getCommPort(PORT);
            comPort.openPort();
            comPort.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                        return;
                    byte[] newData = new byte[comPort.bytesAvailable()];
                    comPort.readBytes(newData, newData.length);
                    String rawDataT = new String(newData);
                    if (rawDataT.startsWith("*")) {
                        rawData = rawDataT;
                    } else {
                        rawData += rawDataT;
                    }
                    if (rawData.trim().endsWith("+")) {
                        Collection<RawDto> rawDtos = deserialize(rawData);
                        rawDataService.save(rawDtos);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
