package com.crypto.electrimate.hardware.repository;

import com.crypto.electrimate.hardware.entity.Module;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sahan Ranasinghe on 3/21/18.
 */
@Repository
public interface ModuleRepository extends CrudRepository<Module, Long> {

}
