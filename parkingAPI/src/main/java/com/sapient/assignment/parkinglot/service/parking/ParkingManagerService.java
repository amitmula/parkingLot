package com.sapient.assignment.parkinglot.service.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;
import com.sapient.assignment.parkinglot.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingManagerService {

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private ParkingSlotService parkingSlotService;

    @Autowired
    private VehicleService vehicleService;

    public ParkingSlot park(String reg_number) {
        if(vehicleService.isVehicleExists(reg_number)) {
            Vehicle vehicle = vehicleService.getVehicleByRegNum(reg_number); //get updated from the service
            ParkingSlot parkingSlot = parkingSlotService.findVehicle(vehicle);
            if(null != parkingSlot) {
                throw new RuntimeException("Vehicle is already parked in the parking slot id : " + parkingSlot.getSlotId());
            } else {
                return parkingLotService.park(vehicle);
            }
        } else {
            Vehicle vehicleInfo = vehicleService.getVehicleInfo(reg_number);
            return parkingLotService.park(vehicleService.createVehicle(vehicleInfo));
        }
    }
}
