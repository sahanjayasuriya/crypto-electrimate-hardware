package com.crypto.electrimate.hardware.entity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Entity
public class Sensor extends SuperEntity implements Serializable {

    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
