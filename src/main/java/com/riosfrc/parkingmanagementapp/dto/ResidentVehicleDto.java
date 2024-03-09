package com.riosfrc.parkingmanagementapp.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentVehicleDto {

    private String plateNumber;

    private String type;

    private LocalDateTime entryTime;

    private Integer stayDuration;
}
