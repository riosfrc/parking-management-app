package com.riosfrc.parkingmanagementapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resident_vehicle")
@DiscriminatorValue("RESIDENT")
@Getter
@Setter
@NoArgsConstructor
public class ResidentVehicle extends Vehicle {
    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "stay_duration")
    private Integer stayDuration;
}
