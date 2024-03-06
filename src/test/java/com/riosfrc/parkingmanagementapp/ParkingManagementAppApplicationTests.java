package com.riosfrc.parkingmanagementapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.riosfrc.parkingmanagementapp.entity.NonResidentVehicle;
import com.riosfrc.parkingmanagementapp.entity.OfficialVehicle;
import com.riosfrc.parkingmanagementapp.entity.ResidentVehicle;
import com.riosfrc.parkingmanagementapp.entity.ParkingStay;
import com.riosfrc.parkingmanagementapp.entity.Vehicle;
import com.riosfrc.parkingmanagementapp.enums.VehicleType;
import com.riosfrc.parkingmanagementapp.repository.NonResidentVehicleRepository;
import com.riosfrc.parkingmanagementapp.repository.OfficialVehicleRepository;
import com.riosfrc.parkingmanagementapp.repository.ParkingStayRepository;
import com.riosfrc.parkingmanagementapp.repository.ResidentVehicleRepository;
import com.riosfrc.parkingmanagementapp.repository.VehicleRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ParkingManagementAppApplicationTests {

	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private ResidentVehicleRepository residentVehicleRepository;
	@Autowired
	private NonResidentVehicleRepository nonResidentVehicleRepository;
	@Autowired
	private OfficialVehicleRepository officialVehicleRepository;
	@Autowired
	private ParkingStayRepository parkingStayRepository;

	@Test
	@Order(1)
	void saveResidentVehicle() {
		ResidentVehicle residentVehicle = new ResidentVehicle();
		residentVehicle.setPlateNumber("ABC123");
		residentVehicle.setEntryTime(LocalDateTime.now());
		residentVehicle.setStayDuration(0);

		ResidentVehicle savedResidentVehicle = residentVehicleRepository.save(residentVehicle);
		
		Vehicle retrivedVehicle = residentVehicleRepository.findById(savedResidentVehicle.getId()).orElse(null);
		assertNotNull(retrivedVehicle);
	}

	@Test
	@Order(2)
	void saveNonResidentVehicle() {
		NonResidentVehicle nonResidentVehicle = new NonResidentVehicle();
		nonResidentVehicle.setPlateNumber("A1B2C3");
		nonResidentVehicle.setEntryTime(LocalDateTime.now());
		
		NonResidentVehicle savedNonResidentVehicle = nonResidentVehicleRepository.save(nonResidentVehicle);

		Vehicle retrivedVehicle = vehicleRepository.findById(savedNonResidentVehicle.getId()).orElse(null);
		assertNotNull(retrivedVehicle);
	}

	@Test
	@Order(3)
	void saveOfficialVehicle() {
		OfficialVehicle officialVehicle = new OfficialVehicle();
		officialVehicle.setPlateNumber("XYZ789");
		officialVehicle.setIsActive(false);

		Vehicle savedOfficialVehicle = officialVehicleRepository.save(officialVehicle);

		Vehicle retrivedVehicle = vehicleRepository.findById(savedOfficialVehicle.getId()).orElse(null);
		assertNotNull(retrivedVehicle);
	}

	@Test
	@Order(4)
	void saveParkingStayInVehicle(){
		String plateNumber = "XYZ789";

		Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber).orElse(null);

		assertNotNull(vehicle, "Vehicle not found");
		assertTrue(vehicle.getType().equals(VehicleType.OFFICIAL.toString()), "Vehicle type is not official");

		ParkingStay parkingStay = new ParkingStay();
		parkingStay.setVehicle(vehicle);
		parkingStay.setEntryTime(LocalDateTime.now());
		ParkingStay savedParkingStay = parkingStayRepository.save(parkingStay);

		assertNotNull(savedParkingStay);

		OfficialVehicle officialVehicle = (OfficialVehicle) vehicle;
		officialVehicle.setIsActive(true);
		vehicle = officialVehicleRepository.save(officialVehicle);
	}

	@Test
	@Order(5)
	void findVehicleByPlateNumber(){
		String plateNumber = "XYZ789";

		Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber).orElse(null);
		assertNotNull(vehicle, "Vehicle not found");

		if (vehicle.getType().equals(VehicleType.RESIDENT.toString())) {
			ResidentVehicle residentVehicle = (ResidentVehicle) vehicle;
			assertNotNull(residentVehicle.getEntryTime(), "Entry time is null for resident vehicle");
			assertEquals(residentVehicle.getStayDuration(), 0);
		} else if (vehicle.getType().equals(VehicleType.NON_RESIDENT.toString())) {
			NonResidentVehicle nonResidentVehicle = (NonResidentVehicle) vehicle;
			assertNotNull(nonResidentVehicle.getEntryTime(), "Entry time is null for non-resident vehicle");
		} else if (vehicle.getType().equals(VehicleType.OFFICIAL.toString())) {
			OfficialVehicle officialVehicle = (OfficialVehicle) vehicle;
			assertNotNull(officialVehicle.getIsActive(), "Is active is null for official vehicle");
		} else {
			fail("Vehicle type is not recognized or is null");
		}
	}
}
