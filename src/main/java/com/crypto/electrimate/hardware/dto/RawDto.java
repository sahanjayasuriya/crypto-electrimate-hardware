package com.crypto.electrimate.hardware.dto;

import java.math.BigDecimal;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
public class RawDto {

    private BigDecimal v;
    private BigDecimal i;
    private Integer pin;
    private Long sensorId;

    public BigDecimal getV() {
        return v;
    }

    public void setV(BigDecimal v) {
        this.v = v;
    }

    public BigDecimal getI() {
        return i;
    }

    public void setI(BigDecimal i) {
        this.i = i;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }
}
