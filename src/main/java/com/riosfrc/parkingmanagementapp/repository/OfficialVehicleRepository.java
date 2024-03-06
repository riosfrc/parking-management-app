package com.riosfrc.parkingmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riosfrc.parkingmanagementapp.entity.Vehicle;

@Repository
public interface OfficialVehicleRepository extends JpaRepository<Vehicle, Long> {

}
