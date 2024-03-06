package com.riosfrc.parkingmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riosfrc.parkingmanagementapp.entity.ResidentVehicle;

@Repository
public interface ResidentVehicleRepository extends JpaRepository<ResidentVehicle, Long> {

}
