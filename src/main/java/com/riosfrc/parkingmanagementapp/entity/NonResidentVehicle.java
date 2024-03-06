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
@Table(name = "nonresident_vehicle")
@DiscriminatorValue("NON_RESIDENT")
@Getter
@Setter
@NoArgsConstructor
public class NonResidentVehicle extends Vehicle{
    @Column(name = "entry_time")
    private LocalDateTime entryTime;
}
