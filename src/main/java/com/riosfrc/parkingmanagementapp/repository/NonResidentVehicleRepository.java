package com.riosfrc.parkingmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riosfrc.parkingmanagementapp.entity.NonResidentVehicle;

@Repository
public interface NonResidentVehicleRepository extends JpaRepository<NonResidentVehicle, Long>{

}
