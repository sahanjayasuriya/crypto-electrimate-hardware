package com.crypto.electrimate.hardware.repository;

import com.crypto.electrimate.hardware.entity.Raw;
import com.crypto.electrimate.hardware.entity.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Repository
public interface RawRepository extends CrudRepository<Raw, Long> {

    Collection<Raw> findFirst100BySensorAndUploaded(Sensor sensor, boolean uploaded);

}
