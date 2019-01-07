package com.sapient.assignment.parkinglot.repository.vehicle;

import com.sapient.assignment.parkinglot.domain.vehicle.Vehicle;

import java.util.List;

public interface VehicleRepository {
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle getById(Integer vehicle_id);
    Boolean isVehicleExists(String reg_num);
    List<Vehicle> getAll();
    Vehicle getVehicleByRegNum(String reg_number);
}
