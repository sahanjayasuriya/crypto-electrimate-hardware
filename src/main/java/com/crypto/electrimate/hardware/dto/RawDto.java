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
    private Long timeDiff;


    public RawDto(BigDecimal v, BigDecimal i, Long timeDiff, Integer pin) {
        this.v = v;
        this.i = i;
        this.pin = pin;
        this.timeDiff = timeDiff;
        this.dateTime = System.currentTimeMillis();
    }

    public RawDto(BigDecimal v, BigDecimal i, Long dateTime, Long timeDiff, Integer pin) {
        this.v = v;
        this.i = i;
        this.dateTime = dateTime;
        this.timeDiff = timeDiff;
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

    public Long getTimeDiff() {
        return timeDiff;
    }

    public void setTimeDiff(Long timeDiff) {
        this.timeDiff = timeDiff;
    }

}
