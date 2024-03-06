package com.riosfrc.parkingmanagementapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.riosfrc.parkingmanagementapp.entity.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE v.plateNumber = :plateNumber")
    Optional<Vehicle> findByPlateNumber(@Param("plateNumber") String plateNumber);

    //@Query("SELECT v.type FROM Vehicle v WHERE v.id = :id")
    //String findTypeById(@Param("id") Long id);
}
