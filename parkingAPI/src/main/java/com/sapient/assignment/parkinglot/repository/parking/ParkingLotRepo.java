package com.sapient.assignment.parkinglot.repository.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingLot;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;

import java.util.List;

public interface ParkingLotRepo {
    ParkingLot createParkingLot(ParkingLot parkingLot);
    ParkingLot getById(Integer parking_lot_id);
    List<ParkingLot> getAll();
}
