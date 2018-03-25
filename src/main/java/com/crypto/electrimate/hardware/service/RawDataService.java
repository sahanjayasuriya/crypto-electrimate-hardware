package com.crypto.electrimate.hardware.service;

import com.crypto.electrimate.hardware.dto.RawDto;
import com.crypto.electrimate.hardware.entity.Raw;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
public interface RawDataService {

    Raw save(RawDto rawDto);

}
