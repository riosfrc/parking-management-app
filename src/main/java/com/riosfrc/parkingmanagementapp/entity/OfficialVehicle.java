package com.riosfrc.parkingmanagementapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "official_vehicle")
@DiscriminatorValue("OFFICIAL")
@Getter
@Setter
@NoArgsConstructor
public class OfficialVehicle extends Vehicle{
    @Column(name = "is_active")
    private Boolean isActive;
}
