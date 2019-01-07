package com.sapient.assignment.parkinglot.repository.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingFloor;
import com.sapient.assignment.parkinglot.domain.parking.ParkingLot;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;

import java.util.List;

public interface ParkingFloorRepo {
    ParkingFloor createParkingFloor(ParkingFloor parkingFloor);
    ParkingFloor getById(Integer parking_floor_id);
    List<ParkingFloor> getAvailableFloor(ParkingLot lot);
    ParkingSlot parkVehicle(Vehicle vehicle);
}
