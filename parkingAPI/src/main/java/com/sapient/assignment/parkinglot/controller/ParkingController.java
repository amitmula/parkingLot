package com.sapient.assignment.parkinglot.controller;

import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.parking.SlotSize;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.service.parking.ParkingManagerService;
import com.sapient.assignment.parkinglot.service.parking.ParkingSlotService;
import com.sapient.assignment.parkinglot.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/parking")
public class ParkingController {

    @Autowired
    ParkingManagerService parkingManagerService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ParkingSlotService parkingSlotService;

    @RequestMapping("/get/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.findAll();
    }

    @RequestMapping("/get/slots")
    public List<ParkingSlot> getAllSlots() {
        return parkingSlotService.findAll();
    }

    @RequestMapping("/get/vehicleinfo/{vehicle_number}")
    public Vehicle getVehicleInfo(@PathVariable String vehicle_number) {
        return vehicleService.getVehicleInfo(vehicle_number);
    }

    @RequestMapping("/save/vehicleinfo/{vehicle_number}")
    public Vehicle saveVehicleInfo(@PathVariable String vehicle_number) {
        return vehicleService.saveVehicleInfo(vehicle_number);
    }

    @RequestMapping("/allocate/{vehicle_number}")
    public ParkingSlot getParkingSlot(@PathVariable String vehicle_number) {
        return parkingManagerService.park(vehicle_number);
    }
}
