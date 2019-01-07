package com.sapient.assignment.parkinglot.service.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingManagerService {

    @Autowired
    ParkingLotService parkingLotService;

    @Autowired
    ParkingSlotService parkingSlotService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    ParkingManagerService parkingManagerService;

    public ParkingSlot park(String reg_number) {
        Vehicle vehicleInfo = vehicleService.getVehicleInfo(reg_number);

        if(vehicleService.isVehicleExists(reg_number)) {
            Vehicle vehicle = vehicleService.getVehicleByRegNum(reg_number);
            ParkingSlot parkingSlot = parkingSlotService.findVehicle(vehicle);
            if(null != parkingSlot) {
                throw new RuntimeException("Vehicle is already parked in the parking slot id : " + parkingSlot.getSlotId());
            } else {
                return parkingLotService.park(vehicle);
            }
        } else {
            return parkingLotService.park(vehicleService.createVehicle(vehicleInfo));
        }
    }
}
