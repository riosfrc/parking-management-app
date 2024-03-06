package com.riosfrc.parkingmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riosfrc.parkingmanagementapp.entity.ParkingStay;

@Repository
public interface ParkingStayRepository extends JpaRepository<ParkingStay, Long>{

}
