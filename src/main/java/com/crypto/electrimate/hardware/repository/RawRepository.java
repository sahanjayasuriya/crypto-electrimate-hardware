package com.crypto.electrimate.hardware.repository;

import com.crypto.electrimate.hardware.dto.SensorDto;
import com.crypto.electrimate.hardware.entity.Raw;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Repository
public interface RawRepository extends CrudRepository<Raw, Long> {

    /**
     * Query for raw records by PIN number and the Uploaded Status
     *
     * @param pin      the connected pin of sensor
     * @param uploaded upload status
     * @return First 100 records matching the given criteria
     */
    Collection<Raw> findFirst100ByPinAndUploaded(Integer pin, boolean uploaded);

    /**
     * Find and return all the PINs recorded in the system
     *
     * @return all the PINs in the form of SensorDto Collection
     */
    @Query("SELECT new com.crypto.electrimate.hardware.dto.SensorDto(r.pin) FROM Raw r WHERE r.uploaded=0 GROUP BY r.pin")
    Collection<SensorDto> findAllPins();

}
