package com.riosfrc.parkingmanagementapp.exception;

public class UnexpectedVehicleTypeException extends RuntimeException {
    public UnexpectedVehicleTypeException(String expectedType){
        super("Vehicle is not a " + expectedType.toLowerCase() + " type");
    }
}
