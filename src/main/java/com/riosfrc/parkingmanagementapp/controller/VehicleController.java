package com.riosfrc.parkingmanagementapp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    
    @PostMapping("/register-resident")
    public void saveResidentVehicle(){

    }

    @PostMapping("/register-non-resident")
    public void saveNonResidentVehicle(){

    }

    @PostMapping("/register-official")
    public void saveOfficialVehicle(){

    }

    @PatchMapping("/register-entry")
    public void updateRegisterEntry(){

    }

    @PatchMapping("/check-out")
    public void updateCheckOut(){
        
    }

    @PatchMapping("/reset-")

    @DeleteMapping("/delete-all-parking-stays")
    public void deleteAllParkingStays(){

    }

    @GetMapping("/resident-payments")
    public void getResidentPayments(){

    }

}
