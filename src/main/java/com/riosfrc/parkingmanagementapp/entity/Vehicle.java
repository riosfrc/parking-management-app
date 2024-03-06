package com.riosfrc.parkingmanagementapp.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "vehicle")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "plate_number", unique = true)
    @NotNull(message = "Plate number cannot be null")
    @NotBlank(message = "Plate number cannot be blank")
    @Size(min = 6, max = 8, message = "Plate number must be between 2 and 8 characters")
    private String plateNumber;

    @Column(name="type", insertable = false, updatable = false)
    private String type;

    @OneToMany(mappedBy = "vehicle")
    private List<ParkingStay> parkingStays;
}
