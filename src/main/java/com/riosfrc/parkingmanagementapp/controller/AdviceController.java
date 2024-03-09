package com.riosfrc.parkingmanagementapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.riosfrc.parkingmanagementapp.exception.InvalidVehicleException;
import com.riosfrc.parkingmanagementapp.exception.UnexpectedVehicleTypeException;
import com.riosfrc.parkingmanagementapp.exception.VehicleAlreadyCreatedException;
import com.riosfrc.parkingmanagementapp.exception.VehicleNotFoundException;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<?> vehicleNotFoundExceptionHandler(VehicleNotFoundException exception) {
        Map<String, Object> response = createResponseMessage(404, "Not Found", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UnexpectedVehicleTypeException.class)
    public ResponseEntity<?> unexpectedVehicleTypeExceptionHandler(UnexpectedVehicleTypeException exception) {
        Map<String, Object> response = createResponseMessage(400, "Bad Request", exception.getMessage());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(VehicleAlreadyCreatedException.class)
    public ResponseEntity<?> vehicleAlreadyCreatedException(VehicleAlreadyCreatedException exception) {
        Map<String, Object> response = createResponseMessage(400, "Bad Request", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidVehicleException.class)
    public ResponseEntity<?> invalidVehicleException(InvalidVehicleException exception) {
        Map<String, Object> response = createResponseMessage(400, "Bad Request", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private Map<String, Object> createResponseMessage(int status, String error, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("error", error);
        response.put("message", message);
        return response;
    }
}
