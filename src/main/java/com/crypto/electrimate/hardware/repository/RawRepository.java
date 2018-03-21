package com.crypto.electrimate.hardware.repository;

import com.crypto.electrimate.hardware.entity.Raw;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Repository
public interface RawRepository extends CrudRepository<Raw, Long> {
}
