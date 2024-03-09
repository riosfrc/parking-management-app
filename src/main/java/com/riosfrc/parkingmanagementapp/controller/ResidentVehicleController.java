package com.riosfrc.parkingmanagementapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riosfrc.parkingmanagementapp.dto.ResidentVehicleDto;
import com.riosfrc.parkingmanagementapp.entity.ResidentVehicle;
import com.riosfrc.parkingmanagementapp.entity.Vehicle;
import com.riosfrc.parkingmanagementapp.enums.VehicleType;
import com.riosfrc.parkingmanagementapp.service.ResidentVehicleService;
import com.riosfrc.parkingmanagementapp.service.VehicleValidationService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/resident-vehicle")
public class ResidentVehicleController {

    @Autowired
    ResidentVehicleService vehicleResidentService;
    @Autowired
    VehicleValidationService vehicleValidationService;

    @GetMapping("/{plateNumber}")
    public ResponseEntity<?> getResidentVehicleById(@PathVariable String plateNumber){
        Vehicle vehicle = vehicleValidationService.validateVehicleExists(plateNumber);
        vehicleValidationService.validateVehicleType(vehicle.getType(), VehicleType.RESIDENT.toString());

        ResidentVehicleDto residentVehicleDto = vehicleResidentService.residentVehicleToResidentVehicleDTO((ResidentVehicle) vehicle);
        
        return ResponseEntity.ok().body(residentVehicleDto);
    }

    @PostMapping("/{plateNumber}")
    public ResponseEntity<?> saveResidentVehicle(@PathVariable String plateNumber) {
        vehicleValidationService.validateVehicleNoExists(plateNumber);

        ResidentVehicle residentVehicle = vehicleResidentService.builtResidentVehicle(plateNumber);

        vehicleValidationService.validateVehicle(residentVehicle);

        vehicleResidentService.saveResidentVehicle(residentVehicle);

        Map<String, Object> bodyResponse = vehicleResidentService.createResponseMessage(201,"Created", "Resident vehicle with plate number " + plateNumber + " was successfully created");
        return ResponseEntity.status(HttpStatus.CREATED).body(bodyResponse);
    }

    @PatchMapping("/")
    public ResponseEntity<?> resetStayDurationAllResidentVehicles() {
        List<ResidentVehicle> residentVehicles = vehicleResidentService.findAllResidentVehicles();
        residentVehicles = vehicleResidentService.resetStayDurationAllResidentVehicles(residentVehicles);

        vehicleResidentService.saveAllResidentVehicle(residentVehicles);

        Map<String, Object> bodyResponse = vehicleResidentService.createResponseMessage(200, "Ok", "All Resident vehicles were successfully updated");
        return ResponseEntity.ok().body(bodyResponse);
    }
    
    @DeleteMapping("/{plateNumber}")
    public ResponseEntity<?> deleteResidentVehicle(@PathVariable String plateNumber) {
        Vehicle vehicle = vehicleValidationService.validateVehicleExists(plateNumber);
        vehicleValidationService.validateVehicleType(vehicle.getType(), VehicleType.RESIDENT.toString());
        vehicleResidentService.deleteResidentVehicleByPlateNumber(plateNumber);

        Map<String, Object> bodyResponse = vehicleResidentService.createResponseMessage(200, "Ok", "Resident vehicle with plates " + plateNumber + " was successfully deleted");
        return ResponseEntity.ok().body(bodyResponse);
    }
}
