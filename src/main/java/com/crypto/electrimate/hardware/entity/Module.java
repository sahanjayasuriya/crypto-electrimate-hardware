package com.crypto.electrimate.hardware.entity;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Sahan Ranasinghe on 3/26/18.
 */
@Entity
public class Module extends SuperEntity implements Serializable {

    private String serialNo;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
}
