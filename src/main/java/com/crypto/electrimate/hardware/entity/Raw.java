package com.crypto.electrimate.hardware.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Entity
public class Raw extends SuperEntity implements Serializable {

    private BigDecimal voltage;
    private BigDecimal current;
    private Long dateTime;
    @ManyToOne(targetEntity = Sensor.class)
    private Sensor sensor;

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public BigDecimal getCurrent() {
        return current;
    }

    public void setCurrent(BigDecimal current) {
        this.current = current;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
