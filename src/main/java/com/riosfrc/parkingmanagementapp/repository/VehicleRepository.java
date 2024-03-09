package com.riosfrc.parkingmanagementapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.riosfrc.parkingmanagementapp.entity.Vehicle;

import jakarta.transaction.Transactional;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE v.plateNumber = :plateNumber")
    Optional<Vehicle> findByPlateNumber(@Param("plateNumber") String plateNumber);

    @Query("SELECT v FROM Vehicle v WHERE v.type = :type")
    Optional<List<Vehicle>> findAllByType(@Param("type") String type);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vehicle v WHERE v.plateNumber = :plateNumber")
    void deleteByPlateNumber(@Param("plateNumber") String plateNumber);
}
