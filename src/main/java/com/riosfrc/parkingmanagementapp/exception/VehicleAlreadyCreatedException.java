package com.riosfrc.parkingmanagementapp.exception;

public class VehicleAlreadyCreatedException extends RuntimeException {
    public VehicleAlreadyCreatedException(String plateNumber) {
        super("Vehicle with plate number " + plateNumber + " already created");
    }
}
