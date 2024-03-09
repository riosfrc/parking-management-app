package com.riosfrc.parkingmanagementapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.riosfrc.parkingmanagementapp.dto.ResidentVehicleDto;
import com.riosfrc.parkingmanagementapp.entity.ResidentVehicle;

@Mapper
public interface ResidentVehicleMapper {

    ResidentVehicleMapper INSTANCE = Mappers.getMapper(ResidentVehicleMapper.class);

    ResidentVehicleDto residentVehicleToResidentVehicleDTO(ResidentVehicle residentVehicle);
}
