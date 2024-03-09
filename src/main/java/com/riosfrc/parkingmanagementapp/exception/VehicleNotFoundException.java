package com.riosfrc.parkingmanagementapp.exception;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(String plateNumber) {
        super("Vehicle with plate number " + plateNumber + " not found");
    }
}
