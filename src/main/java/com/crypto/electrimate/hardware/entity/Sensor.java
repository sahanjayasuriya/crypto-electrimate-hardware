package com.crypto.electrimate.hardware.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"pin"}), @UniqueConstraint(columnNames = {"serialNumber"})})
public class Sensor extends SuperEntity implements Serializable {

    private String serialNumber;
    private Integer pin;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }
}
