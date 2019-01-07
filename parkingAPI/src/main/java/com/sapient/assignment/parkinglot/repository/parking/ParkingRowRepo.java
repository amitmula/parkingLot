package com.sapient.assignment.parkinglot.repository.parking;

import com.sapient.assignment.parkinglot.domain.parking.ParkingFloor;
import com.sapient.assignment.parkinglot.domain.parking.ParkingRow;
import com.sapient.assignment.parkinglot.domain.parking.ParkingSlot;
import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;

import java.util.List;

public interface ParkingRowRepo {
    ParkingRow createParkingRow(ParkingRow parkingRow);
    ParkingRow getById(Integer parking_row_id);
    List<ParkingRow> getAvailableRows(ParkingFloor floor);
    ParkingSlot parkVehicle(Vehicle vehicle);
}
