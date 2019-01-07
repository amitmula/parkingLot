package com.sapient.assignment.parkinglot.domain.vehicle;

public class Bike extends TwoWheeler {

    public Bike(String name, String colour, String regNumber) {
        super(name, colour, regNumber, VehicleType.BIKE);
    }
}
