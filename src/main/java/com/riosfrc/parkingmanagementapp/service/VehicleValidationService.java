package com.riosfrc.parkingmanagementapp.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.riosfrc.parkingmanagementapp.entity.Vehicle;
import com.riosfrc.parkingmanagementapp.enums.VehicleType;
import com.riosfrc.parkingmanagementapp.exception.InvalidVehicleException;
import com.riosfrc.parkingmanagementapp.exception.UnexpectedVehicleTypeException;
import com.riosfrc.parkingmanagementapp.exception.VehicleAlreadyCreatedException;
import com.riosfrc.parkingmanagementapp.exception.VehicleNotFoundException;
import com.riosfrc.parkingmanagementapp.repository.VehicleRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
@Validated
public class VehicleValidationService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private Validator validator;

    public Vehicle validateVehicleExists(String plateNumber){
        return vehicleRepository.findByPlateNumber(plateNumber).orElseThrow(() -> new VehicleNotFoundException(plateNumber));
    }

    public void validateVehicle(Vehicle residentVehicle){
        Set<ConstraintViolation<Vehicle>> violations = validator.validate(residentVehicle);
        if (!violations.isEmpty())
            throw new InvalidVehicleException(violations.toString());
    }

    public void validateVehicleNoExists(String plateNumber) {
        vehicleRepository.findByPlateNumber(plateNumber).ifPresent(value -> {
            throw new VehicleAlreadyCreatedException(plateNumber);
        });
    }

    public void validateVehicleType(String vehicleType, String expectedType) {
        if (!vehicleType.equals(expectedType))
            throw new UnexpectedVehicleTypeException(VehicleType.RESIDENT.toString());
    }
}
