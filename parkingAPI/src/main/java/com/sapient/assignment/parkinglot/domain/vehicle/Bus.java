package com.sapient.assignment.parkinglot.domain.vehicle;

public class Bus extends FourWheeler {

    public Bus(String name, String colour, String regNumber) {
        super(name, colour, regNumber, VehicleType.BUS);
    }
}
