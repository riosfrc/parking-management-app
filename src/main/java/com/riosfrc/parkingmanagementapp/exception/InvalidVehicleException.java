package com.riosfrc.parkingmanagementapp.exception;

public class InvalidVehicleException extends RuntimeException {
    
    public InvalidVehicleException(String message){
        super("Validation failed on vehicle creation: " + message);
    }
}
