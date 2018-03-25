package com.crypto.electrimate.hardware.dto;

import java.math.BigDecimal;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
public class RawDto {

    private Long dateTime;
    private BigDecimal v;
    private BigDecimal i;
    private Integer pin;
    private String serialNumber;


    public RawDto(BigDecimal v, BigDecimal i, Integer pin) {
        this.v = v;
        this.i = i;
        this.pin = pin;
    }

    public RawDto(BigDecimal v, BigDecimal i, Long dateTime, String serialNumber) {
        this.v = v;
        this.i = i;
        this.dateTime = dateTime;
        this.serialNumber = serialNumber;
    }

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

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }
}
