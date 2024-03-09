package com.riosfrc.parkingmanagementapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riosfrc.parkingmanagementapp.dto.ResidentVehicleDto;
import com.riosfrc.parkingmanagementapp.entity.ResidentVehicle;
import com.riosfrc.parkingmanagementapp.entity.Vehicle;
import com.riosfrc.parkingmanagementapp.enums.VehicleType;
import com.riosfrc.parkingmanagementapp.mapper.ResidentVehicleMapper;
import com.riosfrc.parkingmanagementapp.repository.ResidentVehicleRepository;
import com.riosfrc.parkingmanagementapp.repository.VehicleRepository;

import jakarta.validation.Valid;

@Service
public class ResidentVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ResidentVehicleRepository residentVehicleRepository;

    public List<ResidentVehicle> findAllResidentVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAllByType(VehicleType.RESIDENT.toString()).orElse(null);
        return vehicles.stream().map(v -> (ResidentVehicle) v).collect(Collectors.toList());
    }

    public ResidentVehicle saveResidentVehicle(@Valid ResidentVehicle residentVehicle) {
        return residentVehicleRepository.save(residentVehicle);
    }

    public void saveAllResidentVehicle(List<ResidentVehicle> residentVehicles) {
        residentVehicleRepository.saveAll(residentVehicles);
    }

    public void deleteResidentVehicleByPlateNumber(String plateNumber) {
        vehicleRepository.deleteByPlateNumber(plateNumber);
    }

    // Utility methods

    public ResidentVehicleDto residentVehicleToResidentVehicleDTO(ResidentVehicle residentVehicle){
        return ResidentVehicleMapper.INSTANCE.residentVehicleToResidentVehicleDTO(residentVehicle);
    }

    public ResidentVehicle builtResidentVehicle(String plateNumber) {
        ResidentVehicle residentVehicle = new ResidentVehicle();
		residentVehicle.setPlateNumber(plateNumber);
		residentVehicle.setEntryTime(null);
		residentVehicle.setStayDuration(0);
        return residentVehicle;
    }

    public List<ResidentVehicle> resetStayDurationAllResidentVehicles (List<ResidentVehicle> residentVehicles) {
        return residentVehicles.stream()
            .filter(v -> v.getStayDuration() != 0)
            .peek(v -> v.setStayDuration(0))
            .collect(Collectors.toList());
    }

    public Map<String, Object> createResponseMessage(int status, String description, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("description", description);
        response.put("message", message);
        return response;
    }
}
