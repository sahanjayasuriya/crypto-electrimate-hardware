package com.crypto.electrimate.hardware.repository;

import com.crypto.electrimate.hardware.entity.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long> {

    Sensor findByPin(Integer pin);

    Sensor findBySerialNumber(String serialNumber);

    @Override
    Collection<Sensor> findAll();
}
